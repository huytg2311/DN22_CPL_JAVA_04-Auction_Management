

let auctionTimers = document.getElementsByClassName('js-auction-timer');
let finishTimes = document.getElementsByClassName('js-auction-finish-time');
let timeLeftDisplayers = document.getElementsByClassName('js-auction-time-left');

    setInterval(function () {
        for (let i = 0; i<auctionTimers.length; i++) {
            let finishTime = new Date(finishTimes[i].value).getTime()
            let now = new Date().getTime();
            let timeLeft = finishTime-now

            let hours = Math.floor(timeLeft / (1000 * 60 * 60)).toString();
            let minutes = Math.floor((timeLeft % (1000 * 60 * 60)) / (1000 * 60)).toString();
            let seconds = Math.floor((timeLeft % (1000 * 60)) / 1000).toString();
            hours = hours.length < 2 ? "0" + hours : hours;
            minutes = minutes.length < 2 ? "0" + minutes : minutes;
            seconds = seconds.length < 2 ? "0" + seconds : seconds;

            timeLeftDisplayers[i].innerHTML = hours + ' : ' + minutes + ' : ' + seconds
        }
    }, 1000)


