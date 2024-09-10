SELECT
    Cliente.id_cliente,
    Cliente.razao_social,
    Telefone.numero_telefone
FROM
    Cliente
        JOIN
    Telefone ON Cliente.id_cliente = Telefone.id_cliente
        JOIN
    Estado ON Cliente.id_estado = Estado.id_estado
WHERE
    Estado.uf = 'SP';