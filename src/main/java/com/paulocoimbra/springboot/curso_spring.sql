-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 06, 2021 at 02:53 AM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `curso_spring`
--

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` int(11) NOT NULL,
  `number` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncate table before insert `address`
--

TRUNCATE TABLE `address`;
--
-- Dumping data for table `address`
--

INSERT INTO `address` (`id`, `number`, `street`, `zip_code`, `city_id`, `client_id`) VALUES
(1, '300', 'Rua flores', '312312', 1, 1),
(2, '105', 'Avenida matos', '484854', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncate table before insert `category`
--

TRUNCATE TABLE `category`;
--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'Informática'),
(2, 'Escritório'),
(3, 'Cama, mesa e banho'),
(4, 'Eletronicos'),
(5, 'Jardinagem'),
(6, 'Decoração'),
(7, 'Perfumaria');

-- --------------------------------------------------------

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
CREATE TABLE `city` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `state_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncate table before insert `city`
--

TRUNCATE TABLE `city`;
--
-- Dumping data for table `city`
--

INSERT INTO `city` (`id`, `name`, `state_id`) VALUES
(1, 'Uberlandia', 1),
(2, 'São Paulo', 2),
(3, 'Campinas', 2);

-- --------------------------------------------------------

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `client_type` int(11) DEFAULT NULL,
  `cpf_or_cnpj` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Truncate table before insert `client`
--

TRUNCATE TABLE `client`;
--
-- Dumping data for table `client`
--

INSERT INTO `client` (`id`, `client_type`, `cpf_or_cnpj`, `email`, `name`) VALUES
(1, 1, '1231231232', 'maria@gmail.com', 'Maria Silva');

-- --------------------------------------------------------

--
-- Table structure for table `item_order`
--

DROP TABLE IF EXISTS `item_order`;
CREATE TABLE `item_order` (
  `discount` double DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncate table before insert `item_order`
--

TRUNCATE TABLE `item_order`;
--
-- Dumping data for table `item_order`
--

INSERT INTO `item_order` (`discount`, `price`, `quantity`, `order_id`, `product_id`) VALUES
(0, 2000, 1, 1, 1),
(0, 80, 2, 1, 3),
(100, 800, 1, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `order1`
--

DROP TABLE IF EXISTS `order1`;
CREATE TABLE `order1` (
  `id` int(11) NOT NULL,
  `placement` datetime DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `shipment_address_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncate table before insert `order1`
--

TRUNCATE TABLE `order1`;
--
-- Dumping data for table `order1`
--

INSERT INTO `order1` (`id`, `placement`, `client_id`, `shipment_address_id`) VALUES
(1, '2017-09-30 10:32:00', 1, 1),
(2, '2017-10-10 19:35:00', 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `order_id` int(11) NOT NULL,
  `payment_status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncate table before insert `payment`
--

TRUNCATE TABLE `payment`;
--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`order_id`, `payment_status`) VALUES
(1, 2),
(2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `payment_with_bill`
--

DROP TABLE IF EXISTS `payment_with_bill`;
CREATE TABLE `payment_with_bill` (
  `expiration_date` datetime DEFAULT NULL,
  `payment_date` datetime DEFAULT NULL,
  `order_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncate table before insert `payment_with_bill`
--

TRUNCATE TABLE `payment_with_bill`;
--
-- Dumping data for table `payment_with_bill`
--

INSERT INTO `payment_with_bill` (`expiration_date`, `payment_date`, `order_id`) VALUES
('2017-10-20 00:00:00', NULL, 2);

-- --------------------------------------------------------

--
-- Table structure for table `payment_with_card`
--

DROP TABLE IF EXISTS `payment_with_card`;
CREATE TABLE `payment_with_card` (
  `number_of_installments` int(11) DEFAULT NULL,
  `order_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncate table before insert `payment_with_card`
--

TRUNCATE TABLE `payment_with_card`;
--
-- Dumping data for table `payment_with_card`
--

INSERT INTO `payment_with_card` (`number_of_installments`, `order_id`) VALUES
(6, 1);

-- --------------------------------------------------------

--
-- Table structure for table `phone_number`
--

DROP TABLE IF EXISTS `phone_number`;
CREATE TABLE `phone_number` (
  `client_id` int(11) NOT NULL,
  `phone_numbers` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncate table before insert `phone_number`
--

TRUNCATE TABLE `phone_number`;
--
-- Dumping data for table `phone_number`
--

INSERT INTO `phone_number` (`client_id`, `phone_numbers`) VALUES
(1, '867588758'),
(1, '12312323');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncate table before insert `product`
--

TRUNCATE TABLE `product`;
--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `name`, `price`) VALUES
(1, 'Computador', 2000),
(2, 'Impressora', 800),
(3, 'Mouse', 80),
(4, 'Mesa de escritório', 300),
(5, 'Toalha', 50),
(6, 'Colcha', 200),
(7, 'TV true color', 1200),
(8, 'Roçadeira', 800),
(9, 'Abajour', 100),
(10, 'Pendente', 180),
(11, 'Shampoo', 90);

-- --------------------------------------------------------

--
-- Table structure for table `product_category`
--

DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
  `product_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncate table before insert `product_category`
--

TRUNCATE TABLE `product_category`;
--
-- Dumping data for table `product_category`
--

INSERT INTO `product_category` (`product_id`, `category_id`) VALUES
(1, 1),
(1, 4),
(2, 1),
(2, 2),
(2, 4),
(3, 1),
(3, 4),
(4, 2),
(5, 3),
(6, 3),
(7, 4),
(8, 5),
(9, 6),
(10, 6),
(11, 7);

-- --------------------------------------------------------

--
-- Table structure for table `state`
--

DROP TABLE IF EXISTS `state`;
CREATE TABLE `state` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Truncate table before insert `state`
--

TRUNCATE TABLE `state`;
--
-- Dumping data for table `state`
--

INSERT INTO `state` (`id`, `name`) VALUES
(1, 'Minas Gerais'),
(2, 'São paulo');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKpo044ng5x4gynb291cv24vtea` (`city_id`),
  ADD KEY `FK7156ty2o5atyuy9f6kuup9dna` (`client_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `city`
--
ALTER TABLE `city`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK6p2u50v8fg2y0js6djc6xanit` (`state_id`);

--
-- Indexes for table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_bfgjs3fem0hmjhvih80158x29` (`email`);

--
-- Indexes for table `item_order`
--
ALTER TABLE `item_order`
  ADD PRIMARY KEY (`order_id`,`product_id`),
  ADD KEY `FKkybbpxcya7d6pdtyrhpmckdyl` (`product_id`);

--
-- Indexes for table `order1`
--
ALTER TABLE `order1`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK150y69qr93lhd90w9tvie5nql` (`client_id`),
  ADD KEY `FKlb23e629y6yvtoejykargnjij` (`shipment_address_id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`order_id`);

--
-- Indexes for table `payment_with_bill`
--
ALTER TABLE `payment_with_bill`
  ADD PRIMARY KEY (`order_id`);

--
-- Indexes for table `payment_with_card`
--
ALTER TABLE `payment_with_card`
  ADD PRIMARY KEY (`order_id`);

--
-- Indexes for table `phone_number`
--
ALTER TABLE `phone_number`
  ADD KEY `FKkidesb3pyab44og7c42blvxbg` (`client_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product_category`
--
ALTER TABLE `product_category`
  ADD KEY `FKkud35ls1d40wpjb5htpp14q4e` (`category_id`),
  ADD KEY `FK2k3smhbruedlcrvu6clued06x` (`product_id`);

--
-- Indexes for table `state`
--
ALTER TABLE `state`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `address`
--
ALTER TABLE `address`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `city`
--
ALTER TABLE `city`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `order1`
--
ALTER TABLE `order1`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `state`
--
ALTER TABLE `state`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `address`
--
ALTER TABLE `address`
  ADD CONSTRAINT `FK7156ty2o5atyuy9f6kuup9dna` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `FKpo044ng5x4gynb291cv24vtea` FOREIGN KEY (`city_id`) REFERENCES `city` (`id`);

--
-- Constraints for table `city`
--
ALTER TABLE `city`
  ADD CONSTRAINT `FK6p2u50v8fg2y0js6djc6xanit` FOREIGN KEY (`state_id`) REFERENCES `state` (`id`);

--
-- Constraints for table `item_order`
--
ALTER TABLE `item_order`
  ADD CONSTRAINT `FK43h2rv0bolgxt0bltfcklrffe` FOREIGN KEY (`order_id`) REFERENCES `order1` (`id`),
  ADD CONSTRAINT `FKkybbpxcya7d6pdtyrhpmckdyl` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `order1`
--
ALTER TABLE `order1`
  ADD CONSTRAINT `FK150y69qr93lhd90w9tvie5nql` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `FKlb23e629y6yvtoejykargnjij` FOREIGN KEY (`shipment_address_id`) REFERENCES `address` (`id`);

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `FKm1dbe7wr8oc1i9xxuorhfiqia` FOREIGN KEY (`order_id`) REFERENCES `order1` (`id`);

--
-- Constraints for table `payment_with_bill`
--
ALTER TABLE `payment_with_bill`
  ADD CONSTRAINT `FKbs6alekepaafifmcr4avg15l4` FOREIGN KEY (`order_id`) REFERENCES `payment` (`order_id`);

--
-- Constraints for table `payment_with_card`
--
ALTER TABLE `payment_with_card`
  ADD CONSTRAINT `FK2csiaxrq7ghq3ukgtwiv4i5pf` FOREIGN KEY (`order_id`) REFERENCES `payment` (`order_id`);

--
-- Constraints for table `phone_number`
--
ALTER TABLE `phone_number`
  ADD CONSTRAINT `FKkidesb3pyab44og7c42blvxbg` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`);

--
-- Constraints for table `product_category`
--
ALTER TABLE `product_category`
  ADD CONSTRAINT `FK2k3smhbruedlcrvu6clued06x` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `FKkud35ls1d40wpjb5htpp14q4e` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
