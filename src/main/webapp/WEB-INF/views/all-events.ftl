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
        <td>
            <button>
                <a href="booking/tickets?eventName=${event.getName()}&time=${event.getDateTime()}&auditorium=${event.getAuditorium().getName()}"/>
                Tickets
            </button>
        </td>
    </tr>
</#list>
</table>
<form action="/mvc/mvc/events/load" method="Post" enctype="multipart/form-data">
    <input type="file" name="file">
    <button>LOAD EVENTS</button>
</form>
</body>
</html>