package com.ride.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ride.admin.common.ApiResponse;
import com.ride.admin.entity.City;
import com.ride.admin.service.CityService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cities")
public class CityController {
    private final CityService service;

    public CityController(CityService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<Page<City>> list(@RequestParam(defaultValue = "1") int page,
                                        @RequestParam(defaultValue = "10") int size,
                                        @RequestParam(required = false) String name) {
        Page<City> p = new Page<>(page, size);
        if (name != null && !name.isEmpty()) {
            return ApiResponse.ok(service.lambdaQuery().like(City::getName, name).page(p));
        }
        return ApiResponse.ok(service.page(p));
    }

    @PostMapping
    public ApiResponse<City> create(@RequestBody City c) {
        service.save(c);
        return ApiResponse.ok(c);
    }

    @PutMapping("/{id}")
    public ApiResponse<City> update(@PathVariable Long id, @RequestBody City c) {
        c.setId(id);
        service.updateById(c);
        return ApiResponse.ok(c);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.removeById(id);
        return ApiResponse.ok(null);
    }
}


