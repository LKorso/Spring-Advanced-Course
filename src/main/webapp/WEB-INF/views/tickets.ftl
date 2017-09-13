<html>
<head>
    <title>Tickets</title>
</head>
<body>
<p>Avaliable seats: </p>
<form action="tickets/book" method="POST">
    <input type="hidden" name="eventName" value="${eventName}">
    <input type="hidden" name="time" value="${time}">
    <input type="hidden" name="auditorium" value="${auditorium}">
    <#list seats as seat>
        <input type="checkbox" id=${seat} name="selectedSeats" value=${seat} >
        <label for=${seat}>Seat # ${seat}  </label>
    </#list>
    <button>BOOK TICKETS</button>
</form>
<#if tickets??>
<form action="tickets/print/pdf" method="POST">
    <table>
        <td>Event: </td>
        <td>Date: </td>
        <td>Auditorium: </td>
        <td>Seats: </td>
        <td>Price: </td>
        <#list tickets as ticket>
            <td>${ticket.getEvent().getName()}</td>
            <td>${ticket.getDateTime()}</td>
            <td>${ticket.getEvent().getAuditorium().getName()}</td>
            <td>${ticket.getSeats()}</td>
            <td>${ticket.getPrice()}</td>
        </#list>
    </table>
    <input type="hidden" name="eventName" value="${eventName}">
    <input type="hidden" name="time" value="${time}">
    <input type="hidden" name="auditorium" value="${auditorium}">
    <button>DOWNLOAD</button>
</form>
</#if>
</body>
</html>