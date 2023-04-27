create table tools (
                       id varchar(36),
                       tool_code varchar(20),
                       tool_type varchar(20),
                       brand varchar(20),
                       constraint tools_pk primary key (id)
);

insert into tools(id, tool_code, tool_type, brand)
values (gen_random_uuid(), 'CHNS','Chainsaw','Stihl'),
       (gen_random_uuid(), 'LADW','Ladder','Werner'),
       (gen_random_uuid(), 'JAKD', 'Jackhammer','DeWalt'),
       (gen_random_uuid(), 'JAKR', 'Jackhammer','Ridgid');

create table rates (
                       id varchar(36),
                       tool_type varchar(20),
                       daily_charge double precision,
                       weekday_charge boolean default true,
                       weekend_charge boolean default true,
                       holiday_charge boolean default true,
                       constraint rates_pk primary key (id)
);

insert into rates(id, tool_type, daily_charge, weekday_charge, weekend_charge, holiday_charge)
values (gen_random_uuid(), 'Ladder', 1.99, true, true, false),
       (gen_random_uuid(), 'Chainsaw', 1.49, true, false, true),
       (gen_random_uuid(), 'Jackhammer', 2.99, true, false, false);


create table rentals (
                         id varchar(36),
                         tool_code varchar(20),
                         tool_type varchar(20),
                         brand varchar(20),
                         daily_rental_charge double precision,
                         rental_days int,
                         checkout_date date,
                         due_date date,
                         charge_days int,
                         pre_discount_charge double precision,
                         discount_percent int,
                         discount_amount double precision,
                         final_charge double precision,
                         constraint rentals_pk primary key (id)
);