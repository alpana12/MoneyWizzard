/*
Created: 12/4/2016
Modified: 12/4/2016
Model: MySQL 5.7
Database: MySQL 5.7
*/


-- Create tables section -------------------------------------------------

-- Table Person

CREATE TABLE `Person`
(
  `personId` Varchar(36) NOT NULL,
  `name` Varchar(50) NOT NULL,
  `emailId` Text NOT NULL,
  `password` Varchar(20) NOT NULL,
  `photo` Blob,
  `phoneNumber` Int
)
;

ALTER TABLE `Person` ADD  PRIMARY KEY (`personId`)
;

-- Table MoneyWizardAccount

CREATE TABLE `MoneyWizardAccount`
(
  `mwaId` Varchar(36) NOT NULL,
  `accountBalance` Float NOT NULL,
  `personId` Varchar(36) NOT NULL
)
;

CREATE INDEX `IX_Relationship4` ON `MoneyWizardAccount` (`personId`)
;

ALTER TABLE `MoneyWizardAccount` ADD  PRIMARY KEY (`mwaId`)
;

-- Table friends

CREATE TABLE `friends`
(
  `personId` Varchar(36) NOT NULL,
  `friendId` Varchar(36) NOT NULL
)
;

ALTER TABLE `friends` ADD  PRIMARY KEY (`personId`,`friendId`)
;

-- Table MWAccountHistory

CREATE TABLE `MWAccountHistory`
(
  `mwaHistoryId` Varchar(36) NOT NULL,
  `amount` Float NOT NULL,
  `date` Date,
  `recieverpersonId` Varchar(36),
  `senderpersonId` Varchar(36)
)
;

CREATE INDEX `IX_Relationship7` ON `MWAccountHistory` (`recieverpersonId`)
;

CREATE INDEX `IX_Relationship8` ON `MWAccountHistory` (`senderpersonId`)
;

ALTER TABLE `MWAccountHistory` ADD  PRIMARY KEY (`mwaHistoryId`)
;

-- Table ExpenseCategory

CREATE TABLE `ExpenseCategory`
(
  `ExpenseCategoryId` Varchar(36) NOT NULL,
  `categoryName` Varchar(60)
)
;

ALTER TABLE `ExpenseCategory` ADD  PRIMARY KEY (`ExpenseCategoryId`)
;

-- Table Expenses

CREATE TABLE `Expenses`
(
  `expenseId` Varchar(36) NOT NULL,
  `amount` Float NOT NULL,
  `Location` Text NOT NULL,
  `image` Blob,
  `personId` Varchar(36),
  `ExpenseCategoryId` Varchar(36)
)
;

CREATE INDEX `IX_Relationship9` ON `Expenses` (`personId`)
;

CREATE INDEX `IX_Relationship10` ON `Expenses` (`ExpenseCategoryId`)
;

ALTER TABLE `Expenses` ADD  PRIMARY KEY (`expenseId`)
;

-- Table Group

CREATE TABLE `Group`
(
  `groupId` Varchar(36) NOT NULL,
  `name` Varchar(60),
  `image` Blob
)
;

ALTER TABLE `Group` ADD  PRIMARY KEY (`groupId`)
;

-- Table budget

CREATE TABLE `budget`
(
  `budgetId` Varchar(36) NOT NULL,
  `personId` Varchar(36),
  `year` Int,
  `month` Int,
  `amount` Float
)
;

CREATE INDEX `IX_Relationship13` ON `budget` (`personId`)
;

ALTER TABLE `budget` ADD  PRIMARY KEY (`budgetId`)
;

-- Table GroupExpense

CREATE TABLE `GroupExpense`
(
  `groupExpenseId` Varchar(36) NOT NULL,
  `paidByPersonId` Varchar(36),
  `groupId` Varchar(36),
  `amount` Float,
  `comment` Text,
  `image` Blob
)
;

CREATE INDEX `IX_Relationship14` ON `GroupExpense` (`paidByPersonId`)
;

CREATE INDEX `IX_Relationship15` ON `GroupExpense` (`groupId`)
;

ALTER TABLE `GroupExpense` ADD  PRIMARY KEY (`groupExpenseId`)
;

-- Table GroupExpenseMapping

CREATE TABLE `GroupExpenseMapping`
(
  `groupMappingId` Varchar(36) NOT NULL,
  `amount` Float NOT NULL,
  `personId` Varchar(36),
  `groupExpenseId` Varchar(36)
)
;

CREATE INDEX `IX_Relationship16` ON `GroupExpenseMapping` (`personId`)
;

CREATE INDEX `IX_Relationship17` ON `GroupExpenseMapping` (`groupExpenseId`)
;

ALTER TABLE `GroupExpenseMapping` ADD  PRIMARY KEY (`groupMappingId`)
;

-- Table GroupMapping

CREATE TABLE `GroupMapping`
(
  `personId` Varchar(36) NOT NULL,
  `groupId` Varchar(36) NOT NULL
)
;

ALTER TABLE `GroupMapping` ADD  PRIMARY KEY (`personId`,`groupId`)
;

-- Create relationships section ------------------------------------------------- 

ALTER TABLE `friends` ADD CONSTRAINT `Relationship1` FOREIGN KEY (`personId`) REFERENCES `Person` (`personId`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `friends` ADD CONSTRAINT `Relationship3` FOREIGN KEY (`friendId`) REFERENCES `Person` (`personId`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `MoneyWizardAccount` ADD CONSTRAINT `Relationship4` FOREIGN KEY (`personId`) REFERENCES `Person` (`personId`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `MWAccountHistory` ADD CONSTRAINT `Relationship7` FOREIGN KEY (`recieverpersonId`) REFERENCES `Person` (`personId`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `MWAccountHistory` ADD CONSTRAINT `Relationship8` FOREIGN KEY (`senderpersonId`) REFERENCES `Person` (`personId`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `Expenses` ADD CONSTRAINT `Relationship9` FOREIGN KEY (`personId`) REFERENCES `Person` (`personId`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `Expenses` ADD CONSTRAINT `Relationship10` FOREIGN KEY (`ExpenseCategoryId`) REFERENCES `ExpenseCategory` (`ExpenseCategoryId`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `GroupMapping` ADD CONSTRAINT `Relationship11` FOREIGN KEY (`personId`) REFERENCES `Person` (`personId`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `GroupMapping` ADD CONSTRAINT `Relationship12` FOREIGN KEY (`groupId`) REFERENCES `Group` (`groupId`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `budget` ADD CONSTRAINT `Relationship13` FOREIGN KEY (`personId`) REFERENCES `Person` (`personId`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `GroupExpense` ADD CONSTRAINT `Relationship14` FOREIGN KEY (`paidByPersonId`) REFERENCES `Person` (`personId`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `GroupExpense` ADD CONSTRAINT `Relationship15` FOREIGN KEY (`groupId`) REFERENCES `Group` (`groupId`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `GroupExpenseMapping` ADD CONSTRAINT `Relationship16` FOREIGN KEY (`personId`) REFERENCES `Person` (`personId`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `GroupExpenseMapping` ADD CONSTRAINT `Relationship17` FOREIGN KEY (`groupExpenseId`) REFERENCES `GroupExpense` (`groupExpenseId`) ON DELETE RESTRICT ON UPDATE RESTRICT
;

ALTER TABLE `moneywizzard`.`expenses` 
ADD COLUMN `date` DATE NULL AFTER `ExpenseCategoryId`;

ALTER TABLE `moneywizzard`.`groupexpense` 
ADD COLUMN `date` DATE NULL AFTER `image`;


ALTER TABLE `moneywizzard`.`mwaccounthistory` 
ADD COLUMN `comment` VARCHAR(200) NULL AFTER `senderpersonId`;

INSERT INTO `moneywizzard`.`expensecategory` (`ExpenseCategoryId`, `categoryName`) VALUES ('1', 'Food and Drinks');
INSERT INTO `moneywizzard`.`expensecategory` (`ExpenseCategoryId`, `categoryName`) VALUES ('2', 'Shopping');
INSERT INTO `moneywizzard`.`expensecategory` (`ExpenseCategoryId`, `categoryName`) VALUES ('3', 'Health');
INSERT INTO `moneywizzard`.`expensecategory` (`ExpenseCategoryId`, `categoryName`) VALUES ('4', 'Groceries');
INSERT INTO `moneywizzard`.`expensecategory` (`ExpenseCategoryId`, `categoryName`) VALUES ('5', 'Travel');
INSERT INTO `moneywizzard`.`expensecategory` (`ExpenseCategoryId`, `categoryName`) VALUES ('6', 'Rent');
INSERT INTO `moneywizzard`.`expensecategory` (`ExpenseCategoryId`, `categoryName`) VALUES ('7', 'Misc');
