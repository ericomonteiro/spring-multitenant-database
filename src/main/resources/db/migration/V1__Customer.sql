create table public.customer
(
	id varchar(36) not null
		constraint customer_pkey
			primary key,
	name varchar(255),
	email varchar(255)
);

