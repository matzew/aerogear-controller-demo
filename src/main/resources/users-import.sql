insert into roles values (1, 'user', 'The default role given to all users.');
insert into roles values (2, 'admin', 'The administrator role only given to site admins');
insert into roles_permissions values (2, 'user:*');
insert into users(id,username,email,password) values (1, 'admin', 'sample@shiro.apache.org', ' new Sha256Hash(admin).toHex()');
insert into users_roles values (1, 2);