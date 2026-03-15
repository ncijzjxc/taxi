Get-ChildItem -Recurse -Filter *.java admin-backend/src/main/java | ForEach-Object {
 $content = Get-Content $_.FullName -Raw
 $content = $content.Replace('jakarta.persistence','javax.persistence')
 $content = $content.Replace('jakarta.servlet','javax.servlet')
 Set-Content -Path $_.FullName -Value $content
}
