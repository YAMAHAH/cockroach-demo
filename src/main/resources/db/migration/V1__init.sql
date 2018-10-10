CREATE TABLE accounts (
	id BIGINT NOT NULL,
	balance BIGINT NULL,
	CONSTRAINT "primary" PRIMARY KEY (id ASC),
	INDEX balance_idx (balance ASC),
	FAMILY "primary" (id, balance)
);

CREATE TABLE account_items (
	id BIGINT NOT NULL,
	balance BIGINT NULL,
	account_id BIGINT NULL,
	CONSTRAINT "primary" PRIMARY KEY (id ASC),
	CONSTRAINT fkam1ce779rh25v8r4pqk81mct9 FOREIGN KEY (account_id) REFERENCES accounts (id) ON UPDATE CASCADE ON DELETE CASCADE,
	INDEX account_items_account_id_idx (account_id ASC),
	FAMILY "primary" (id, balance, account_id)
);
