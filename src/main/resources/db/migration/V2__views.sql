-- VIEWS --

CREATE VIEW invoice_view as
select i.*,c.fullname cliente_fullname
from invoice i JOIN client c
ON i.client_id = c.id;