<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>HallCinema</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">
</head>
<body>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
    $(document).ready(function () {
        load();
        setInterval(function () {
            load();
        }, 5000);
    });

    function load() {
        $.ajax({
            type: "GET",
            url: './hall',
            dataType: 'json',
            complete: function ($data) {
                let json = JSON.parse($data.responseText);
                let result = '';
                for (let i = 1; i <= 3; i++) {
                    result += '<tr><th> ' + i + ' </th>';
                    for (let j = 1; j <= 3; j++) {
                        let number = i.toString() + j.toString();
                        let occupied = json[number];
                        if (occupied) {
                            result += '<td style="background-color: red" >'
                                + '<input disabled type="radio" name="place" value="' + number + '">'
                                + ' Ряд ' + i + ', место ' + j + ' (Забронировано) </td>';
                        } else {
                            result += '<td style="background-color: lightgreen">' +
                                '<input type="radio" name="place" value="' + number + '"> ' +
                                'Ряд ' + i + ', место ' + j + ' </td>';
                        }
                    }
                    result += '</tr>';
                }
                document.getElementById("hall").innerHTML = result;
            }
        })
    }
</script>
<script>
    function choosePlace() {
        let result = '0';
        let radios = document.getElementsByName("place");
        for (let i = 0; i < radios.length; i++) {
            if (radios[i].checked) {
                result = radios[i].value;
                break;
            }
        }
        return result;
    }

    function validate() {
        let result = true;
        if (choosePlace() === '0') {
            document.getElementById("error").innerHTML = 'Не выбрано место для оформления заказа!';
            result = false;
        }
        return result;
    }

    function pay() {
        if (validate()) {
            window.location.href = "/cinema/payment.html?place=" + choosePlace();
        }
    }
</script>
<div class="container">
    <div class="row pt-3">
        <h4>
            Бронирование мест на сеанс
        </h4>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th style="width: 120px;">Ряд \ Место</th>
                <th>1</th>
                <th>2</th>
                <th>3</th>
            </tr>
            </thead>
            <tbody id="hall">
            </tbody>
        </table>
    </div>
    <div class="row float-right">
        <div id="error" class="container" style="background: red">
        </div>
        <button type="button" class="btn btn-success" onclick="pay()">Оплатить</button>
    </div>
</div>
</body>
</html>
