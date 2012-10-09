<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vendas Cadastradas</title>
    </head>
    <body>
        <h3>Vendas Cadastradas</h3>
        <p>
        <table border="1">
            <tr>
                <th>Data da Venda</th>
                <th>Nome comprador</th>
                <th>Produtos comprados</th>
                <th>Valor total da venda</th>
            </tr>
            <tr>
                <c:forEach var="venda" items="${vendas}">
                <tr>
                    <th><c:out value="${venda.data_venda}" /></th>
                    <th><c:out value="${venda.nome_usuario}" /></th>
                    <th>
                        <c:forEach var="produto" items="${venda.produtos}">
                            <c:out value="${produto.desc_Produto}" />
                            <br/>
                        </c:forEach>
                    </th>
                    <th><c:out value="${venda.valor_venda}" /></th>
                </tr>
            </c:forEach>
        </table>
    </p>
    <p>
        <a href="index.htm">Voltar</a>
    </p>
</body>
</html>