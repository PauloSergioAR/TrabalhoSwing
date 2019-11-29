
CREATE TABLE public.Cliente (
                CPF INTEGER NOT NULL,
                Nome VARCHAR NOT NULL,
                CONSTRAINT cpf PRIMARY KEY (CPF)
);


CREATE SEQUENCE public.produto_codigo_produto_seq;

CREATE TABLE public.Produto (
                Codigo_Produto INTEGER NOT NULL DEFAULT nextval('public.produto_codigo_produto_seq'),
                Estoque INTEGER NOT NULL,
                Nome VARCHAR NOT NULL,
                Preco NUMERIC(2) NOT NULL,
                CONSTRAINT codigo_produto PRIMARY KEY (Codigo_Produto)
);


ALTER SEQUENCE public.produto_codigo_produto_seq OWNED BY public.Produto.Codigo_Produto;

CREATE SEQUENCE public.vendedor_codigo_seq;

CREATE TABLE public.Vendedor (
                Codigo INTEGER NOT NULL DEFAULT nextval('public.vendedor_codigo_seq'),
                Nome VARCHAR NOT NULL,
                CONSTRAINT codigo PRIMARY KEY (Codigo)
);


ALTER SEQUENCE public.vendedor_codigo_seq OWNED BY public.Vendedor.Codigo;

CREATE SEQUENCE public.compra_id_seq;

CREATE TABLE public.Compra (
                idVendedor INTEGER NOT NULL,
                CPF INTEGER NOT NULL,
                Compra_id INTEGER NOT NULL DEFAULT nextval('public.compra_id_seq'),
                Data DATE NOT NULL,
                CONSTRAINT codigo_compra PRIMARY KEY (idVendedor, CPF, Compra_id)
);


ALTER SEQUENCE public.compra_id_seq OWNED BY public.Compra.Compra_id;

CREATE SEQUENCE public.itemcompra_codigo_item_seq;

CREATE TABLE public.ItemCompra (
                Codigo_Item INTEGER NOT NULL DEFAULT nextval('public.itemcompra_codigo_item_seq'),
                Codigo INTEGER NOT NULL,
                idVendedor INTEGER NOT NULL,
                CPF INTEGER NOT NULL,
                Compra_id INTEGER NOT NULL,
                Quantidade INTEGER NOT NULL,
                CONSTRAINT codigo_item PRIMARY KEY (Codigo_Item, Codigo, idVendedor, CPF, Compra_id)
);


ALTER SEQUENCE public.itemcompra_codigo_item_seq OWNED BY public.ItemCompra.Codigo_Item;

ALTER TABLE public.Compra ADD CONSTRAINT cliente_compra_fk
FOREIGN KEY (CPF)
REFERENCES public.Cliente (CPF)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.ItemCompra ADD CONSTRAINT produto_itemcompra_fk
FOREIGN KEY (Codigo)
REFERENCES public.Produto (Codigo_Produto)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Compra ADD CONSTRAINT vendedor_compra_id_fk
FOREIGN KEY (idVendedor)
REFERENCES public.Vendedor (Codigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.ItemCompra ADD CONSTRAINT compra_itemcompra_fk
FOREIGN KEY (idVendedor, CPF, Compra_id)
REFERENCES public.Compra (idVendedor, CPF, Compra_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
