-- ----------------------------------------------------------------------------
-- Ofertas Model
-------------------------------------------------------------------------------


-- ---------- Table for validation queries from the connection pool -----------

DROP TABLE PingTable;
CREATE TABLE PingTable (foo CHAR(1));

-- -----------------------------------------------------------------------------
DROP TABLE Reserva;
DROP TABLE Oferta;



-- --------------------------------- Oferta ------------------------------------
CREATE TABLE Oferta ( 
	ofertaId BIGINT NOT NULL AUTO_INCREMENT,
    nombreOferta VARCHAR(255) COLLATE latin1_bin NOT NULL,
    descripcionOferta VARCHAR(1024) COLLATE latin1_bin ,
    estadoOferta VARCHAR(255) NOT NULL,
    precioRealOferta FLOAT NOT NULL,
    precioDescontadoOferta FLOAT NOT NULL,
    comisionOferta FLOAT NOT NULL,
    fechaLimiteOferta TIMESTAMP DEFAULT 0 NOT NULL,
    fechaLimiteReserva TIMESTAMP DEFAULT 0 NOT NULL,
    facebookId VARCHAR(1000),
    CONSTRAINT OfertaId PRIMARY KEY(ofertaId), 
    CONSTRAINT validPrice CHECK ( price >= 0) 
    ) ENGINE = InnoDB;
    
    
-- --------------------------------- Reserva ------------------------------------

CREATE TABLE Reserva ( 
	reservaId BIGINT NOT NULL AUTO_INCREMENT,
    ofertaId BIGINT NOT NULL,
    emailUsuarioReserva VARCHAR(70) COLLATE latin1_bin NOT NULL,
    tarjetaCreditoReserva VARCHAR(16),
    estadoReserva VARCHAR(16),
    fechaCreacionReserva TIMESTAMP DEFAULT 0 NOT NULL,
    CONSTRAINT ReservaPK PRIMARY KEY(reservaId),
    CONSTRAINT ReservaOfertaIdFK FOREIGN KEY(ofertaId)
   		REFERENCES Oferta(ofertaId) ON DELETE CASCADE 
	) ENGINE = InnoDB;