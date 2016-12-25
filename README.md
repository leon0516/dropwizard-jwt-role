#dropwizard-jwt-role

基于dropwizard的Restful服务:
* 基本的jwt认证
* 基本的基于用户角色的资源访问权限控制

这只是一个demo

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