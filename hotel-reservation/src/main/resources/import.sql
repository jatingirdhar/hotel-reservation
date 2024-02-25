INSERT INTO `user` (`id`, `password`, `username`) VALUES ('1', '$2a$10$xzXF6K6OnuetuRovZm.VIODscI4B4/qCy968wYwKLui9C4IwwAO2S', 'test');

INSERT INTO `customer` (`id`, `customer_name`, `user_id`) VALUES ('1', 'Jatin', '1');

INSERT INTO `hotel` (`id`, `name`, `phone`) VALUES ('1', 'Taj', '1234566');
INSERT INTO `hotel` (`id`, `name`, `phone`) VALUES ('2', 'Raddison', '85848484');
INSERT INTO `hotel` (`id`, `name`, `phone`) VALUES ('3', 'Lalit', '4543444');

INSERT INTO `room` (`id`, `no_of_person`, `price`, `room_number`, `type`, `hotel_id`) VALUES ('1', '2', '100.00', '1', 'DELUXE', '1');
INSERT INTO `room` (`id`, `no_of_person`, `price`, `room_number`, `type`, `hotel_id`) VALUES ('2', '4', '200.00', '2', 'DOUBLE', '1');
INSERT INTO `room` (`id`, `no_of_person`, `price`, `room_number`, `type`, `hotel_id`) VALUES ('3', '4', '1000.00', '3', 'SUITE', '1');
INSERT INTO `room` (`id`, `no_of_person`, `price`, `room_number`, `type`, `hotel_id`) VALUES ('4', '2', '100.00', '1', 'DELUXE', '2');
INSERT INTO `room` (`id`, `no_of_person`, `price`, `room_number`, `type`, `hotel_id`) VALUES ('5', '4', '200.00', '2', 'DOUBLE', '2');
INSERT INTO `room` (`id`, `no_of_person`, `price`, `room_number`, `type`, `hotel_id`) VALUES ('6', '4', '1000.00', '3', 'SUITE', '2');
INSERT INTO `room` (`id`, `no_of_person`, `price`, `room_number`, `type`, `hotel_id`) VALUES ('7', '2', '100.00', '1', 'DELUXE', '3');
INSERT INTO `room` (`id`, `no_of_person`, `price`, `room_number`, `type`, `hotel_id`) VALUES ('8', '4', '200.00', '2', 'DOUBLE', '3');
INSERT INTO `room` (`id`, `no_of_person`, `price`, `room_number`, `type`, `hotel_id`) VALUES ('9', '4', '1000.00', '3', 'SUITE', '3');
