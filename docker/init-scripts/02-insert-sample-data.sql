-- Insert sample data for testing
SET search_path TO bookstore, public;

-- Insert authors
INSERT INTO authors (first_name, last_name, email, birth_date, biography) VALUES
('George', 'Orwell', 'gorwell@example.com', '1903-06-25', 'English novelist and essayist, journalist and critic.'),
('J.K.', 'Rowling', 'jkrowling@example.com', '1965-07-31', 'British author, best known for the Harry Potter fantasy series.'),
('Stephen', 'King', 'sking@example.com', '1947-09-21', 'American author of horror, supernatural fiction, suspense, and fantasy novels.'),
('Agatha', 'Christie', 'achristie@example.com', '1890-09-15', 'English writer known for her detective novels.'),
('Isaac', 'Asimov', 'iasimov@example.com', '1920-01-02', 'American writer and professor of biochemistry, known for science fiction works.'),
('Jane', 'Austen', 'jausten@example.com', '1775-12-16', 'English novelist known for her social commentary and wit.'),
('Mark', 'Twain', 'mtwain@example.com', '1835-11-30', 'American writer, humorist, entrepreneur, publisher, and lecturer.'),
('William', 'Shakespeare', 'wshakespeare@example.com', '1564-04-23', 'English playwright, poet, and actor.'),
('Charles', 'Dickens', 'cdickens@example.com', '1812-02-07', 'English writer and social critic.'),
('J.R.R.', 'Tolkien', 'jrrtolkien@example.com', '1892-01-03', 'English author, best known for The Hobbit and The Lord of the Rings.');

-- Insert publishers
INSERT INTO publishers (name, address, city, country, phone, website, established_year) VALUES
('Penguin Random House', '1745 Broadway', 'New York', 'USA', '+1-212-555-0100', 'www.penguinrandomhouse.com', 1927),
('HarperCollins', '195 Broadway', 'New York', 'USA', '+1-212-555-0200', 'www.harpercollins.com', 1989),
('Macmillan Publishers', '120 Broadway', 'New York', 'USA', '+1-212-555-0300', 'www.macmillan.com', 1843),
('Simon & Schuster', '1230 Avenue of the Americas', 'New York', 'USA', '+1-212-555-0400', 'www.simonandschuster.com', 1924),
('Hachette Book Group', '1290 Avenue of the Americas', 'New York', 'USA', '+1-212-555-0500', 'www.hachettebookgroup.com', 1837),
('Scholastic', '557 Broadway', 'New York', 'USA', '+1-212-555-0600', 'www.scholastic.com', 1920),
('Oxford University Press', 'Great Clarendon Street', 'Oxford', 'UK', '+44-1865-555-100', 'www.oup.com', 1586),
('Cambridge University Press', 'University Printing House', 'Cambridge', 'UK', '+44-1223-555-200', 'www.cambridge.org', 1534);

-- Insert categories
INSERT INTO categories (name, description, parent_category_id) VALUES
('Fiction', 'Fictional works of literature', NULL),
('Non-Fiction', 'Factual and informative books', NULL),
('Science Fiction', 'Fiction based on imagined future scientific advances', 1),
('Fantasy', 'Fiction with magical and supernatural elements', 1),
('Mystery', 'Fiction dealing with puzzling crimes', 1),
('Romance', 'Fiction focusing on romantic relationships', 1),
('Horror', 'Fiction intended to frighten or disgust', 1),
('Biography', 'Account of someone''s life written by someone else', 2),
('History', 'Books about historical events and periods', 2),
('Science', 'Books about scientific topics', 2),
('Classic Literature', 'Timeless literary works', 1),
('Young Adult', 'Books targeted at teenagers', 1);

-- Insert books
INSERT INTO books (isbn, title, subtitle, author_id, publisher_id, category_id, publication_date, pages, language, price, in_stock, description) VALUES
('978-0452284234', '1984', NULL, 1, 1, 3, '1949-06-08', 328, 'English', 15.99, true, 'A dystopian social science fiction novel.'),
('978-0439139595', 'Harry Potter and the Goblet of Fire', 'Year 4', 2, 6, 4, '2000-07-08', 734, 'English', 29.99, true, 'The fourth novel in the Harry Potter series.'),
('978-1501142970', 'It', NULL, 3, 4, 7, '1986-09-15', 1138, 'English', 25.99, true, 'A horror novel about seven children terrorized by a being.'),
('978-0062073488', 'Murder on the Orient Express', NULL, 4, 2, 5, '1934-01-01', 256, 'English', 14.99, true, 'A detective novel featuring Hercule Poirot.'),
('978-0553293357', 'Foundation', 'Foundation Series Book 1', 5, 1, 3, '1951-05-01', 244, 'English', 16.99, true, 'The first novel in the Foundation series.'),
('978-0141439518', 'Pride and Prejudice', NULL, 6, 1, 11, '1813-01-28', 432, 'English', 12.99, true, 'A romantic novel of manners.'),
('978-0486280615', 'Adventures of Huckleberry Finn', NULL, 7, 1, 11, '1884-12-10', 224, 'English', 11.99, true, 'A novel about a young boy''s adventures.'),
('978-0143128540', 'Hamlet', NULL, 8, 1, 11, '1603-01-01', 342, 'English', 10.99, true, 'The tragedy of Hamlet, Prince of Denmark.'),
('978-0141439556', 'Great Expectations', NULL, 9, 1, 11, '1861-01-01', 544, 'English', 13.99, true, 'A bildungsroman depicting the growth of Pip.'),
('978-0547928227', 'The Hobbit', 'There and Back Again', 10, 3, 4, '1937-09-21', 310, 'English', 19.99, true, 'A fantasy novel and children''s book.'),
('978-0439708180', 'Harry Potter and the Sorcerer''s Stone', 'Year 1', 2, 6, 4, '1997-06-26', 309, 'English', 24.99, true, 'The first novel in the Harry Potter series.'),
('978-1982134488', 'The Shining', NULL, 3, 4, 7, '1977-01-28', 447, 'English', 22.99, true, 'A horror novel about a haunted hotel.'),
('978-0062073501', 'And Then There Were None', NULL, 4, 2, 5, '1939-11-06', 272, 'English', 13.99, true, 'A mystery novel about ten strangers on an island.'),
('978-0553382563', 'I, Robot', NULL, 5, 1, 3, '1950-12-02', 224, 'English', 14.99, true, 'A collection of nine science fiction short stories.'),
('978-0141439693', 'Emma', NULL, 6, 1, 11, '1815-12-23', 512, 'English', 12.99, true, 'A novel about youthful hubris and romantic misunderstandings.');

-- Insert customers
INSERT INTO customers (first_name, last_name, email, phone, address, city, postal_code, country, registration_date) VALUES
('John', 'Doe', 'john.doe@email.com', '+1-555-0101', '123 Main St', 'New York', '10001', 'USA', '2023-01-15'),
('Jane', 'Smith', 'jane.smith@email.com', '+1-555-0102', '456 Oak Ave', 'Los Angeles', '90001', 'USA', '2023-02-20'),
('Michael', 'Johnson', 'michael.j@email.com', '+1-555-0103', '789 Pine Rd', 'Chicago', '60601', 'USA', '2023-03-10'),
('Emily', 'Brown', 'emily.brown@email.com', '+1-555-0104', '321 Elm St', 'Houston', '77001', 'USA', '2023-04-05'),
('David', 'Wilson', 'david.w@email.com', '+1-555-0105', '654 Maple Dr', 'Phoenix', '85001', 'USA', '2023-05-12'),
('Sarah', 'Davis', 'sarah.davis@email.com', '+1-555-0106', '987 Cedar Ln', 'Philadelphia', '19101', 'USA', '2023-06-18'),
('James', 'Miller', 'james.m@email.com', '+1-555-0107', '147 Birch Ct', 'San Antonio', '78201', 'USA', '2023-07-22'),
('Lisa', 'Garcia', 'lisa.garcia@email.com', '+1-555-0108', '258 Spruce Way', 'San Diego', '92101', 'USA', '2023-08-30'),
('Robert', 'Martinez', 'robert.m@email.com', '+1-555-0109', '369 Willow Blvd', 'Dallas', '75201', 'USA', '2023-09-14'),
('Mary', 'Rodriguez', 'mary.r@email.com', '+1-555-0110', '741 Ash Ave', 'San Jose', '95101', 'USA', '2023-10-25');

-- Insert orders
INSERT INTO orders (customer_id, order_date, status, total_amount, shipping_address, shipping_city, shipping_postal_code, shipping_country) VALUES
(1, '2024-01-10 10:30:00', 'delivered', 45.97, '123 Main St', 'New York', '10001', 'USA'),
(2, '2024-01-15 14:45:00', 'delivered', 29.99, '456 Oak Ave', 'Los Angeles', '90001', 'USA'),
(3, '2024-02-01 09:15:00', 'delivered', 68.97, '789 Pine Rd', 'Chicago', '60601', 'USA'),
(1, '2024-02-14 16:20:00', 'delivered', 19.99, '123 Main St', 'New York', '10001', 'USA'),
(4, '2024-02-20 11:30:00', 'shipped', 42.98, '321 Elm St', 'Houston', '77001', 'USA'),
(5, '2024-03-05 13:45:00', 'processing', 54.97, '654 Maple Dr', 'Phoenix', '85001', 'USA'),
(6, '2024-03-10 10:00:00', 'delivered', 14.99, '987 Cedar Ln', 'Philadelphia', '19101', 'USA'),
(7, '2024-03-15 15:30:00', 'pending', 77.96, '147 Birch Ct', 'San Antonio', '78201', 'USA'),
(8, CURRENT_TIMESTAMP - INTERVAL '2 days', 'processing', 35.98, '258 Spruce Way', 'San Diego', '92101', 'USA'),
(9, CURRENT_TIMESTAMP - INTERVAL '1 day', 'pending', 29.98, '369 Willow Blvd', 'Dallas', '75201', 'USA');

-- Insert order items
INSERT INTO order_items (order_id, book_id, quantity, unit_price, discount) VALUES
(1, 1, 1, 15.99, 0),
(1, 5, 1, 16.99, 0),
(1, 9, 1, 13.99, 1.00),
(2, 2, 1, 29.99, 0),
(3, 3, 1, 25.99, 0),
(3, 4, 1, 14.99, 0),
(3, 7, 2, 11.99, 0),
(4, 10, 1, 19.99, 0),
(5, 6, 1, 12.99, 0),
(5, 11, 1, 24.99, 5.00),
(6, 12, 1, 22.99, 0),
(6, 13, 1, 13.99, 0),
(6, 14, 1, 14.99, 0),
(7, 15, 1, 14.99, 0),
(8, 1, 1, 15.99, 0),
(8, 2, 1, 29.99, 10.00),
(9, 3, 1, 25.99, 5.00),
(9, 4, 1, 14.99, 0),
(10, 5, 1, 16.99, 0),
(10, 6, 1, 12.99, 0);

-- Insert reviews
INSERT INTO reviews (book_id, customer_id, rating, title, comment, is_verified_purchase, helpful_count) VALUES
(1, 1, 5, 'A masterpiece of dystopian fiction', 'Orwell''s vision of a totalitarian future is both terrifying and brilliantly conceived.', true, 42),
(2, 2, 5, 'Best Harry Potter book!', 'The Triwizard Tournament was amazing. Couldn''t put it down!', true, 28),
(3, 3, 4, 'Terrifying but compelling', 'Stephen King at his best. The character development is incredible.', true, 35),
(4, 1, 5, 'Classic Christie mystery', 'The plot twists are ingenious. Didn''t see the ending coming!', false, 18),
(5, 5, 4, 'Foundation of modern sci-fi', 'Asimov''s vision of the future is thought-provoking and influential.', true, 22),
(10, 4, 5, 'Timeless adventure', 'The Hobbit is a perfect introduction to Middle-earth. Loved every page!', false, 31),
(11, 2, 5, 'Where it all began', 'The magic of Harry Potter starts here. A must-read for all ages.', false, 45),
(1, 6, 4, 'Frighteningly relevant', 'Reading this in 2024 feels eerily prophetic. Important book.', true, 38),
(6, 7, 5, 'Austen at her finest', 'The wit, the romance, the social commentary - perfection!', false, 16),
(7, 8, 4, 'Great American novel', 'Twain''s humor and social criticism still resonate today.', false, 12);

-- Insert inventory
INSERT INTO inventory (book_id, quantity_available, quantity_reserved, reorder_level, reorder_quantity, last_restock_date, warehouse_location) VALUES
(1, 50, 5, 10, 50, '2024-03-01', 'A1-B2'),
(2, 75, 10, 15, 75, '2024-02-15', 'B3-C1'),
(3, 30, 2, 10, 40, '2024-03-10', 'C2-D3'),
(4, 45, 3, 10, 50, '2024-02-20', 'A2-B1'),
(5, 25, 1, 10, 30, '2024-03-05', 'D1-E2'),
(6, 40, 4, 10, 50, '2024-02-25', 'B1-C2'),
(7, 35, 2, 10, 40, '2024-03-08', 'E3-F1'),
(8, 20, 0, 10, 30, '2024-03-12', 'F2-G3'),
(9, 55, 5, 10, 60, '2024-02-18', 'A3-B4'),
(10, 60, 8, 15, 70, '2024-03-03', 'C3-D2'),
(11, 80, 12, 20, 100, '2024-02-10', 'B2-C3'),
(12, 28, 3, 10, 40, '2024-03-07', 'D3-E1'),
(13, 42, 2, 10, 50, '2024-02-22', 'E2-F3'),
(14, 38, 4, 10, 45, '2024-03-09', 'F1-G2'),
(15, 48, 6, 10, 55, '2024-02-28', 'G3-H1');

-- Add some additional reviews to show aggregation
INSERT INTO reviews (book_id, customer_id, rating, title, comment, is_verified_purchase, helpful_count) VALUES
(1, 9, 5, 'Essential reading', 'Everyone should read this book. More relevant than ever.', false, 29),
(2, 10, 4, 'Great but dark', 'Darker than the previous books but excellently written.', false, 15),
(10, 8, 5, 'Perfect fantasy', 'Tolkien created an entire world. Simply amazing!', false, 24);