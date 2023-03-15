create table if not exists Pizza_Order (
    id identity,
    delivery_Name varchar(50) not null,
    delivery_Street varchar(50) not null,
    delivery_City varchar(50) not null,
    delivery_State varchar(2) not null,
    delivery_Zip varchar(10) not null,
    cc_number varchar(16) not null,
    cc_expiration varchar(5) not null,
    cc_cvv varchar(3) not null,
    placed_at timestamp not null
);

create table if not exists IngredientRef (
    id varchar(4) not null,
    name varchar(25) not null,
    type varchar(10) not null
);

alter table Pizza
    add foreign key (pizza_order) references Pizza_Order(id);
alter table IngredientRef
    add foreign key (ingredient) references Ingredient(id);