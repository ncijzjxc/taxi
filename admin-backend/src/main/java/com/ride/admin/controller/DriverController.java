package com.ride.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ride.admin.common.ApiResponse;
import com.ride.admin.entity.Driver;
import com.ride.admin.service.DriverService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {
    private final DriverService service;

    public DriverController(DriverService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<Page<Driver>> list(@RequestParam(defaultValue = "1") int page,
                                          @RequestParam(defaultValue = "10") int size,
                                          @RequestParam(required = false) String name) {
        Page<Driver> p = new Page<>(page, size);
        if (name != null && !name.isEmpty()) {
            return ApiResponse.ok(service.lambdaQuery().like(Driver::getName, name).page(p));
        }
        return ApiResponse.ok(service.page(p));
    }

    @PostMapping
    public ApiResponse<Driver> create(@RequestBody Driver d) {
        if (d.getUsername() == null || d.getUsername().isEmpty()) {
            throw new RuntimeException("请输入账号");
        }
        if (d.getId() == null) {
            d.setId(generateDriverId());
        }
        service.save(d);
        return ApiResponse.ok(d);
    }

    private long generateDriverId() {
        while (true) {
            long id = 100000L + (long) (Math.random() * 900000L);
            if (service.getById(id) == null) {
                return id;
            }
        }
    }

    @PutMapping("/{id}")
    public ApiResponse<Driver> update(@PathVariable Long id, @RequestBody Driver d) {
        d.setId(id);
        service.updateById(d);
        return ApiResponse.ok(d);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.removeById(id);
        return ApiResponse.ok(null);
    }
}


