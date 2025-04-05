CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TABLE IF EXISTS space_user_members;
DROP TABLE IF EXISTS space_rules;
DROP TYPE IF EXISTS space_permissions;
DROP TABLE IF EXISTS spaces;
DROP TABLE IF EXISTS connections;
DROP TABLE IF EXISTS profiles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_role;
DROP TYPE IF EXISTS platform_permissions;
DROP TABLE IF EXISTS medias;

CREATE TABLE medias(
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    url VARCHAR(1000) NOT NULL,
    type VARCHAR(255) NOT NULL,
    media_id VARCHAR(255) NOT NULL,
    size BIGSERIAL NOT NULL
);

CREATE TYPE platform_permissions AS ENUM ('READ', 'WRITE', 'DELETE');

create TABLE user_role(
    role_name VARCHAR(50) PRIMARY KEY,
    permissions platform_permissions[]
);

INSERT INTO user_role(role_name, permissions) VALUES
    ('USER', '{READ}'),
    ('ADMIN', '{READ, WRITE, DELETE}');

CREATE TABLE users (
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role_id VARCHAR(50) DEFAULT 'USER' REFERENCES user_role(role_name)
);

CREATE TABLE profiles(
    user_id UUID PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    bio VARCHAR(1000),
    media_object_id UUID,
    gender VARCHAR(10) CHECK (
            gender IN ('MALE', 'FEMALE', 'OTHER')
    ),
    date_of_birth DATE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (media_object_id) REFERENCES medias(id) ON DELETE CASCADE
);

CREATE TABLE spaces(
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(1000),
    space_image UUID,
    FOREIGN KEY (space_image) REFERENCES medias(id)
);

CREATE TYPE space_permissions AS ENUM ('CREATE_DISCUSSION', 'CHANGE_DISCUSSION_NAME');

CREATE TABLE space_rules(
    id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    rule_name VARCHAR(50),
    permission space_permissions[],
    space_id UUID,
    FOREIGN KEY (space_id) REFERENCES spaces(id) ON DELETE CASCADE
);

INSERT INTO space_rules(id,rule_name, permission) VALUES
    ('de305d54-75b4-431b-adb2-eb6b9e546014', 'ROOT', '{CREATE_DISCUSSION, CHANGE_DISCUSSION_NAME}'),
    ('d3a6f5c4-5c9b-4b9b-9a2b-eb6b9e546014', 'MEMBER', '{CREATE_DISCUSSION}');

CREATE TABLE space_user_members(
    user_id UUID,
    space_id UUID,
    rule_id UUID DEFAULT 'de305d54-75b4-431b-adb2-eb6b9e546014',
    joined_on DATE DEFAULT CURRENT_DATE,
    PRIMARY KEY(user_id, space_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (space_id) REFERENCES spaces(id) ON DELETE CASCADE,
    FOREIGN KEY (rule_id) REFERENCES space_rules(id) ON DELETE SET NULL
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
