CREATE TABLE IF NOT EXISTS positions (
  position_id bigint AUTO_INCREMENT primary key,
  position_title LONGTEXT NOT NULL
  );

CREATE TABLE IF NOT EXISTS genders (
  gender_id bigint AUTO_INCREMENT primary key,
  gender_title CHAR(10) NOT NULL);

CREATE TABLE IF NOT EXISTS union_members (
  union_member_id bigint AUTO_INCREMENT primary key,
  surname VARCHAR(50) NOT NULL,
  name VARCHAR(50) NOT NULL,
  patronymic VARCHAR(50) NULL,
  birthday DATETIME(6) NOT NULL,
  gender_id bigint NOT NULL,
  position_id bigint NOT NULL,
  CONSTRAINT union_members_positions
    FOREIGN KEY (position_id)
    REFERENCES positions (position_id)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT union_members_genders
    FOREIGN KEY (gender_id)
    REFERENCES genders (gender_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS children (
  child_id bigint AUTO_INCREMENT primary key,
  surname VARCHAR(50) NOT NULL,
  name VARCHAR(50) NOT NULL,
  patronymic VARCHAR(50) NULL,
  birthdate DATETIME(6) NOT NULL,
  gender_id bigint NOT NULL,
  CONSTRAINT children_genders
    FOREIGN KEY (gender_id)
    REFERENCES genders (gender_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE IF NOT EXISTS children_parents (
  id bigint AUTO_INCREMENT primary key,
  child_id bigint NOT NULL,
  union_member_id bigint NOT NULL,
  CONSTRAINT children_parents_union_members
    FOREIGN KEY (union_member_id)
    REFERENCES union_members (union_member_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT children_parents_children
    FOREIGN KEY (child_id)
    REFERENCES children (child_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS public_organizations(
  public_organization_id bigint AUTO_INCREMENT primary key,
  public_organization_title LONGTEXT NOT NULL);

CREATE TABLE IF NOT EXISTS public_org__union_members (
  id bigint AUTO_INCREMENT primary key,
  public_organization_id bigint NOT NULL,
  union_member_id bigint NOT NULL,
  CONSTRAINT public_org__union_members_union_members
    FOREIGN KEY (union_member_id)
    REFERENCES union_members (union_member_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT public_org__union_members_public_organizations
    FOREIGN KEY (public_organization_id)
    REFERENCES public_organizations (public_organization_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS application_types (
  application_type_id bigint AUTO_INCREMENT primary key,
  application_type_title LONGTEXT NOT NULL);

CREATE TABLE IF NOT EXISTS grounds_for_fin_payments (
  ground_id bigint AUTO_INCREMENT primary key,
  ground_text LONGTEXT NOT NULL);

CREATE TABLE IF NOT EXISTS material_payments (
  material_payment_id bigint AUTO_INCREMENT primary key,
  payment_amount DECIMAL(10,4) NOT NULL,
  ground_id bigint NOT NULL,
  CONSTRAINT material_payments_grounds_for_fin_payments
    FOREIGN KEY (ground_id)
    REFERENCES grounds_for_fin_payments (ground_id)
    ON DELETE NO ACTION
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS meeting_minutes (
  meeting_minute_id bigint AUTO_INCREMENT primary key,
  meeting_minute_number INT NOT NULL,
  meeting_minute_date DATETIME(6) NOT NULL,
  meeting_minute_theme LONGTEXT NULL);

CREATE TABLE IF NOT EXISTS applications(
  application_id bigint AUTO_INCREMENT primary key,
  register_date DATETIME(6) NOT NULL,
  union_member_id bigint NOT NULL,
  application_type_id bigint NOT NULL,
  material_payment_id bigint NOT NULL,
  meeting_minute_id bigint NOT NULL,
  CONSTRAINT applications_union_members
    FOREIGN KEY (union_member_id)
    REFERENCES union_members (union_member_id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT applications_application_types
    FOREIGN KEY (application_type_id)
    REFERENCES application_types (application_type_id)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT applications_material_payments
    FOREIGN KEY (material_payment_id)
    REFERENCES material_payments (material_payment_id)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT applications_meeting_minutes
    FOREIGN KEY (meeting_minute_id)
    REFERENCES meeting_minutes (meeting_minute_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS phone_numbers (
  phone_number_id bigint AUTO_INCREMENT primary key,
  union_member_id bigint NOT NULL,
  phone_number VARCHAR(50) CHARACTER SET 'utf8mb4' NULL,
  CONSTRAINT phone_numbers_union_members
    FOREIGN KEY (union_member_id)
    REFERENCES union_members (union_member_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

  create table if not exists roles
  (
      id bigint AUTO_INCREMENT primary key,
      name varchar(255)
  );

  create table if not exists users
  (
      id bigint AUTO_INCREMENT primary key,
      password varchar(255),
      username varchar(255)
  );

  create table if not exists users_roles
  (
      users_id bigint not null references users,
      roles_id bigint not null references roles,
      primary key (users_id, roles_id)
  );