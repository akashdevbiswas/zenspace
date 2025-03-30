CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TABLE IF EXISTS space_members;
DROP TABLE IF EXISTS rules;
DROP TABLE IF EXISTS spaces;
DROP TABLE IF EXISTS connections;
DROP TABLE IF EXISTS profiles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS medias;

CREATE TABLE medias(
    id VARCHAR(255) PRIMARY KEY,
    url VARCHAR(1000) NOT NULL
);

CREATE TABLE users (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE profiles(
    user_id UUID PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    bio VARCHAR(1000),
    media_details VARCHAR(255),
    gender VARCHAR(10),
    avatar VARCHAR(1000),
    date_of_birth DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (media_details) REFERENCES medias(id) ON DELETE CASCADE
);

CREATE TABLE connections(
    user_id UUID,
    connection_id UUID,
    PRIMARY KEY(user_id, connection_id),
    connects_on DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (connection_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE spaces(
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    space_image VARCHAR(255),
    FOREIGN KEY (space_image) REFERENCES medias(id)
);

CREATE TABLE rules(
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    name varchar(255) NOT NULL,
    space_id UUID,
    permission VARCHAR(255) CHECK (
            permission IN ('root','admin', 'moderator', 'member')
            ),
    FOREIGN KEY (space_id) REFERENCES spaces(id)
);

INSERT INTO rules(id, name, permission) VALUES ('670d2f29-1a4d-4a96-a725-7d829c8811d4', 'root', 'root');
INSERT INTO rules(id, name, permission) VALUES ('13e28c5e-4747-4dd8-bb81-b19db4b20655', 'member', 'member');


CREATE TABLE space_members(
    user_id UUID,
    space_id UUID,
    rules_id UUID,
    PRIMARY KEY(user_id, space_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (space_id) REFERENCES spaces(id) ON DELETE CASCADE,
    FOREIGN KEY (rules_id) REFERENCES rules(id) ON DELETE CASCADE
);

create TABLE posts(

)

create TABLE comments(

)
create TABLE reacts(

)

create TABLE topics(

)
create TABLE topic_posts(

)

create TABLE projects(

)

create TABLE issues(

)

create TABLE issue_comments(

)
create TABLE issue_reacts(

)
