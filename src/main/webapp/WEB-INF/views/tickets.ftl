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
</body>
</html>