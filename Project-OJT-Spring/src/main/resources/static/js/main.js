(function ($) {
    "use strict";

    // Spinner
    var spinner = function () {
        setTimeout(function () {
            if ($('#spinner').length > 0) {
                $('#spinner').removeClass('show');
            }
        }, 1);
    };
    spinner();
    
    
    // Initiate the wowjs
    new WOW().init();


    // Fixed Navbar
    // $('.fixed-top').css('top', $('.top-bar').height());
    // $(window).scroll(function () {
    //     if ($(this).scrollTop()) {
    //         $('.fixed-top').addClass('bg-white').css('top', 0);
    //     } else {
    //         $('.fixed-top').removeClass('bg-white').css('top', $('.top-bar').height());
    //     }
    // });
    
    
    // Back to top button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 300) {
            $('.back-to-top').fadeIn('slow');
        } else {
            $('.back-to-top').fadeOut('slow');
        }
    });
    $('.back-to-top').click(function () {
        $('html, body').animate({scrollTop: 0}, 1500, 'easeInOutExpo');
        return false;
    });


    // Header carousel
    $(".header-carousel").owlCarousel({
        autoplay: false,
        smartSpeed: 1500,
        loop: true,
        nav: true,
        dots: false,
        items: 1,
        navText : [
            '<i class="bi bi-chevron-left"></i>',
            '<i class="bi bi-chevron-right"></i>'
        ]
    });


    // Facts counter
    $('[data-toggle="counter-up"]').counterUp({
        delay: 10,
        time: 2000
    });


    // Testimonials carousel
    $(".testimonial-carousel").owlCarousel({
        autoplay: false,
        smartSpeed: 1000,
        margin: 25,
        loop: true,
        center: true,
        dots: false,
        nav: true,
        navText : [
            '<i class="bi bi-chevron-left"></i>',
            '<i class="bi bi-chevron-right"></i>'
        ],
        responsive: {
            0:{
                items:1
            },
            768:{
                items:2
            },
            992:{
                items:3
            }
        }
    });

    //set link for rows of table
    let rows = $('.js-table-link tbody tr');
    for (const row of rows) {
        $(row, 'td').click(function (){
            console.log('a')
            window.location=$(row).data('href');
        })
    }

    //line-clamp-2
    let clampObject = $('.js-line-clamp-2')
    for (const clampObjectElement of clampObject) {
        $clamp(clampObjectElement, {clamp: 2})
    }
    
})(jQuery);

//set height for header-spacer
    let headerHeight = document.getElementById("header").getElementsByClassName("navbar")[0]

    let headerSpacers = document.getElementsByClassName("header-spacer")
    for (const headerSpacer of headerSpacers ){
    headerSpacer.setAttribute("style", "height:" + headerHeight.clientHeight + "px")
}
    // document.getElementById("sidebar").setAttribute("style", "height:calc(100vh - " + headerHeight.clientHeight + "px)")
    document.getElementsByClassName("sidebar-content")[0].setAttribute("style", "height:calc(100vh - " + headerHeight.clientHeight + "px)")


