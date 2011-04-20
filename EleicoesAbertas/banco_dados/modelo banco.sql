SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`situacao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`situacao` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`situacao` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `situacao` VARCHAR(50) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `situacao_UNIQUE` (`situacao` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`partido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`partido` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`partido` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `sigla` VARCHAR(15) NOT NULL ,
  `numero` VARCHAR(5) NOT NULL COMMENT 'Retirado o UNIQUE do número em função de uma regra do programa =/' ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `sigla_UNIQUE` (`sigla` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`cargo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`cargo` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`cargo` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `cargo` VARCHAR(60) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `cargo_UNIQUE` (`cargo` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`coligacao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`coligacao` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`coligacao` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `coligacao` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`estado_civil`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`estado_civil` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`estado_civil` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `estado_civil` VARCHAR(30) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `estado_civil_UNIQUE` (`estado_civil` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ocupacao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`ocupacao` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`ocupacao` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `ocupacao` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `ocupacao_UNIQUE` (`ocupacao` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`nacionalidade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`nacionalidade` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`nacionalidade` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nacionalidade` VARCHAR(60) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `nacionalidade_UNIQUE` (`nacionalidade` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`estado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`estado` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`estado` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(50) NOT NULL ,
  `UF` VARCHAR(3) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `nome_UNIQUE` (`nome` ASC) ,
  UNIQUE INDEX `UF_UNIQUE` (`UF` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`cidade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`cidade` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`cidade` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(100) NOT NULL ,
  `estado_id` INT NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_Cidade_estado1` (`estado_id` ASC) ,
  CONSTRAINT `fk_Cidade_estado1`
    FOREIGN KEY (`estado_id` )
    REFERENCES `mydb`.`estado` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`resultado_eleicao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`resultado_eleicao` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`resultado_eleicao` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `resultado_eleicao` VARCHAR(60) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `resultado_eleicao_UNIQUE` (`resultado_eleicao` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`eleicao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`eleicao` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`eleicao` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `ano` VARCHAR(4) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`grau_instrucao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`grau_instrucao` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`grau_instrucao` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `grau_instrucao` VARCHAR(60) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `grau_instrucao_UNIQUE` (`grau_instrucao` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`sexo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`sexo` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`sexo` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `sexo` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`id`) ,
  UNIQUE INDEX `sexo_UNIQUE` (`sexo` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`candidato`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`candidato` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`candidato` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `nome` VARCHAR(110) NOT NULL ,
  `nome_urna` VARCHAR(50) NOT NULL ,
  `numero_candidato` VARCHAR(10) NOT NULL ,
  `data_nascimento` DATE NOT NULL ,
  `situacao_id` INT NOT NULL ,
  `partido_id` INT NOT NULL ,
  `cargo_id` INT NOT NULL ,
  `coligacao_id` INT NULL ,
  `estado_civil_id` INT NOT NULL ,
  `ocupacao_id` INT NOT NULL ,
  `nacionalidade_id` INT NOT NULL ,
  `cidade_nascimento_id` INT NOT NULL ,
  `resultado_eleicao_id` INT NOT NULL ,
  `eleicao_id` INT NOT NULL ,
  `protocolo` VARCHAR(45) NULL ,
  `processo` VARCHAR(45) NULL ,
  `cnpj_campanha` VARCHAR(45) NULL ,
  `grau_instrucao_id` INT NOT NULL ,
  `sexo_id` INT NOT NULL ,
  `estado_id` INT NOT NULL ,
  PRIMARY KEY (`id`, `numero_candidato`, `estado_id`) ,
  INDEX `fk_candidato_situacao1` (`situacao_id` ASC) ,
  INDEX `fk_candidato_partido1` (`partido_id` ASC) ,
  INDEX `fk_candidato_cargo1` (`cargo_id` ASC) ,
  INDEX `fk_candidato_coligacao1` (`coligacao_id` ASC) ,
  INDEX `fk_candidato_estado_civil1` (`estado_civil_id` ASC) ,
  INDEX `fk_candidato_ocupacao1` (`ocupacao_id` ASC) ,
  INDEX `fk_candidato_nacionalidade1` (`nacionalidade_id` ASC) ,
  INDEX `fk_candidato_cidade1` (`cidade_nascimento_id` ASC) ,
  INDEX `fk_candidato_resultado_eleicao1` (`resultado_eleicao_id` ASC) ,
  INDEX `fk_candidato_eleicao1` (`eleicao_id` ASC) ,
  INDEX `fk_candidato_grau_instrucao1` (`grau_instrucao_id` ASC) ,
  INDEX `fk_candidato_sexo1` (`sexo_id` ASC) ,
  INDEX `fk_candidato_estado1` (`estado_id` ASC) ,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) ,
  CONSTRAINT `fk_candidato_situacao1`
    FOREIGN KEY (`situacao_id` )
    REFERENCES `mydb`.`situacao` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_candidato_partido1`
    FOREIGN KEY (`partido_id` )
    REFERENCES `mydb`.`partido` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_candidato_cargo1`
    FOREIGN KEY (`cargo_id` )
    REFERENCES `mydb`.`cargo` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_candidato_coligacao1`
    FOREIGN KEY (`coligacao_id` )
    REFERENCES `mydb`.`coligacao` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_candidato_estado_civil1`
    FOREIGN KEY (`estado_civil_id` )
    REFERENCES `mydb`.`estado_civil` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_candidato_ocupacao1`
    FOREIGN KEY (`ocupacao_id` )
    REFERENCES `mydb`.`ocupacao` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_candidato_nacionalidade1`
    FOREIGN KEY (`nacionalidade_id` )
    REFERENCES `mydb`.`nacionalidade` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_candidato_cidade1`
    FOREIGN KEY (`cidade_nascimento_id` )
    REFERENCES `mydb`.`cidade` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_candidato_resultado_eleicao1`
    FOREIGN KEY (`resultado_eleicao_id` )
    REFERENCES `mydb`.`resultado_eleicao` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_candidato_eleicao1`
    FOREIGN KEY (`eleicao_id` )
    REFERENCES `mydb`.`eleicao` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_candidato_grau_instrucao1`
    FOREIGN KEY (`grau_instrucao_id` )
    REFERENCES `mydb`.`grau_instrucao` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_candidato_sexo1`
    FOREIGN KEY (`sexo_id` )
    REFERENCES `mydb`.`sexo` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_candidato_estado1`
    FOREIGN KEY (`estado_id` )
    REFERENCES `mydb`.`estado` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`coligacao_partido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`coligacao_partido` ;

CREATE  TABLE IF NOT EXISTS `mydb`.`coligacao_partido` (
  `coligacao_id` INT NOT NULL ,
  `partido_id` INT NOT NULL ,
  PRIMARY KEY (`coligacao_id`, `partido_id`) ,
  INDEX `fk_coligacao_has_partido_partido1` (`partido_id` ASC) ,
  CONSTRAINT `fk_coligacao_has_partido_coligacao1`
    FOREIGN KEY (`coligacao_id` )
    REFERENCES `mydb`.`coligacao` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_coligacao_has_partido_partido1`
    FOREIGN KEY (`partido_id` )
    REFERENCES `mydb`.`partido` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
