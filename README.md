#dropwizard-jwt-role

基于dropwizard的Restful服务:
* 基本的jwt认证
* 基本的基于用户角色的资源访问权限控制

这只是一个demo

导入数据库结构

`java -jar target/basicapi-0.0.1.jar db migrate basicapi.yml`

启动服务

`java -jar target/basicapi-0.0.1.jar server basicapi.yml`


第一次启动只有数据表,没有数据,需要自己先注册一个账号


database table
<table border="1" style="border-collapse:collapse">
<tr><th>ID</th><th>NAME</th><th>PASSWORD</th><th>ROLE</th></tr>
<tr><td>1</td><td>su</td><td>123456</td><td>1</td></tr>
<tr><td>2</td><td>user</td><td>122233</td><td>3</td></tr>
<tr><td>3</td><td>admin</td><td>123456</td><td>2</td></tr></table>

<table border="1" style="border-collapse:collapse">
<tr><th>ROLE_ID</th><th>ROLE_NAME</th></tr>
<tr><td>1</td><td>SUPER_USER</td></tr>
<tr><td>2</td><td>ADMIN</td></tr>
<tr><td>3</td><td>USER</td></tr></table>