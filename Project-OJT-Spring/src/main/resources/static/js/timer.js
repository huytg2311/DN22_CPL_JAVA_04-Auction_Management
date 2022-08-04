// Set the date we're counting down to
let countDownDate = new Date(document.getElementById('timeFinish').value).getTime();

// Update the count down every 1 second
let x = setInterval(function () {

    // Get today's date and time
    let now = new Date().getTime();

    // Find the distance between now and the count down date
    let distance = countDownDate - now;

    // Time calculations for days, hours, minutes and seconds

    let hours = Math.floor(distance / (1000 * 60 * 60)).toString();
    let minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60)).toString();
    let seconds = Math.floor((distance % (1000 * 60)) / 1000).toString();
    hours = hours.length < 2 ? "0" + hours : hours;
    minutes = minutes.length < 2 ? "0" + minutes : minutes;
    seconds = seconds.length < 2 ? "0" + seconds : seconds;

    // Display the result in the element with id="demo"
    document.getElementById("hour").innerHTML = hours
    document.getElementById("minute").innerHTML = minutes
    document.getElementById("second").innerHTML = seconds

    // If the count down is finished, write some text
    if (distance < 0) {
        clearInterval(x);
        document.getElementById("timer").innerHTML = "EXPIRED";
    }
}, 1000);

(function ($){
    $('#subBidBtn').click(function (){
        plus
    })
})(jQuery)