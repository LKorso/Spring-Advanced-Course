<html>
<head>
    <title>Events</title>
</head>
<body>
<table>
    <td>Event name</td>
    <td>Base Price</td>
    <td>Date</td>
    <td>Auditorium</td>
    <#list events as event>
        <tr>
            <td>${event.getName()}</td>
            <td>${event.getBasePrice()}</td>
            <td>${event.getDateTime()}</td>
            <td>${event.getAuditorium().getName()}</td>
        </tr>
    </#list>
</table>
</body>
</html>