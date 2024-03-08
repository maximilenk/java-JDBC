INSERT INTO chatSchema.User(login, password) VALUES
	('pro100paren20', '123qwe'),
	('Oleg', '321ewq'),
	('keklol2007', '213weq'),
	('volodyaXS2012', '456rty'),
	('Demon2006', 'oipasd'),
	('Kroshitel2004', 'qwerty');

SELECT * FROM chatSchema.User;

INSERT INTO chatSchema.Room(name, owner) VALUES
	('work', 2),
	('friends', 2),
	('games', 1),
	('cooking', 3),
	('walking', 4),
	('fishing', 5);

SELECT * FROM chatSchema.Room;

INSERT INTO chatSchema.Message(author, room, text) VALUES
	(1, 1, 'hello'),
	(2, 2, 'good job'),
	(3, 1, 'cool'),
	(4, 4, 'lol'),
	(5, 4, 'bb'),
	(6, 5, 'ahahah'),
	(2, 3, 'tysm');

SELECT * FROM chatSchema.Message;