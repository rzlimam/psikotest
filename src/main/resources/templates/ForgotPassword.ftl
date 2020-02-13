<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml" xmlns:v="urn:schemas-microsoft-com:vml"
  xmlns:o="urn:schemas-microsoft-com:office:office">

<head>
  <meta charset="utf-8"> <!-- utf-8 works for most cases -->
  <meta name="viewport" content="width=device-width"> <!-- Forcing initial-scale shouldn't be necessary -->
  <meta http-equiv="X-UA-Compatible" content="IE=edge"> <!-- Use the latest (edge) version of IE rendering engine -->
  <meta name="x-apple-disable-message-reformatting"> <!-- Disable auto-scale in iOS 10 Mail entirely -->
  <title></title> <!-- The title tag shows in email notifications, like Android 4.4. -->


  <link href="https://fonts.googleapis.com/css?family=Playfair+Display:400,400i,700,700i" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

  <!-- CSS Reset : BEGIN -->
  <style>
    html,
    body {
      margin: 0 auto !important;
      padding: 0 !important;
      height: 100% !important;
      width: 100% !important;
      background: #f1f1f1;
    }

    /* What it does: Stops email clients resizing small text. */
    * {
      -ms-text-size-adjust: 100%;
      -webkit-text-size-adjust: 100%;
    }

    /* What it does: Centers email on Android 4.4 */
    div[style*="margin: 16px 0"] {
      margin: 0 !important;
    }

    /* What it does: Stops Outlook from adding extra spacing to tables. */
    table,
    td {
      mso-table-lspace: 0pt !important;
      mso-table-rspace: 0pt !important;
    }


    /* What it does: Fixes webkit padding issue. */
    table {
      border-spacing: 0 !important;
      border-collapse: collapse !important;
      table-layout: fixed !important;
      margin: 0 auto !important;
    }

    /* What it does: Uses a better rendering method when resizing images in IE. */
    img {
      -ms-interpolation-mode: bicubic;
    }

    /* What it does: Prevents Windows 10 Mail from underlining links despite inline CSS. Styles for underlined links should be inline. */
    a {
      text-decoration: none;
    }

    /* What it does: A work-around for email clients meddling in triggered links. */
    *[x-apple-data-detectors],
    /* iOS */
    .unstyle-auto-detected-links *,
    .aBn {
      border-bottom: 0 !important;
      cursor: default !important;
      color: inherit !important;
      text-decoration: none !important;
      font-size: inherit !important;
      font-family: inherit !important;
      font-weight: inherit !important;
      line-height: inherit !important;
    }

    .center {
      display: block;
      margin-left: auto;
      margin-right: auto;
      width: 60%;
    }

    /* What it does: Prevents Gmail from displaying a download button on large, non-linked images. */
    .a6S {
      display: none !important;
      opacity: 0.01 !important;
    }

    /* What it does: Prevents Gmail from changing the text color in conversation threads. */
    .im {
      color: inherit !important;
    }

    /* If the above doesn't work, add a .g-img class to any image in question. */
    img.g-img+div {
      display: none !important;
    }

    /* What it does: Removes right gutter in Gmail iOS app: https://github.com/TedGoas/Cerberus/issues/89  */
    /* Create one of these media queries for each additional viewport size you'd like to fix */

    /* iPhone 4, 4S, 5, 5S, 5C, and 5SE */
    @media only screen and (min-device-width: 320px) and (max-device-width: 374px) {
      u~div .email-container {
        min-width: 320px !important;
      }
    }

    /* iPhone 6, 6S, 7, 8, and X */
    @media only screen and (min-device-width: 375px) and (max-device-width: 413px) {
      u~div .email-container {
        min-width: 375px !important;
      }
    }

    /* iPhone 6+, 7+, and 8+ */
    @media only screen and (min-device-width: 414px) {
      u~div .email-container {
        min-width: 414px !important;
      }
    }

  </style>

  <!-- CSS Reset : END -->

  <!-- Progressive Enhancements : BEGIN -->
  <style>
    .primary {
      background: #f3a333;
    }

    .bg_white {
      background: #ffffff;
    }

    .bg_whitees {
      background-color: transparent;
    }

    .bg_light {
      background: #fafafa;
    }

    .bg_black {
      background: #000000;
    }

    .bg_dark {
      background: rgba(0, 0, 0, .8);
    }

    .email-section {
      padding: 2.5em;
    }

    /*BUTTON*/
    .btn {
      padding: 10px 15px;
    }

    .btn.btn-primary {
      border-radius: 30px;
      background: #f3a333;
      color: #ffffff;
    }



    h1,
    h2,
    h3,
    h4,
    h5,
    h6 {
      font-family: 'Playfair Display', serif;
      color: #000000;
      margin-top: 0;
    }

    body {
      font-family: 'Montserrat', sans-serif;
      font-weight: 400;
      font-size: 15px;
      line-height: 1.8;
      color: rgba(0, 0, 0, .4);
    }

    a {
      color: #f3a333;
    }

    table {}

    /*LOGO*/

    .logo h1 {
      margin: 0;
    }

    .logo h1 a {
      color: #000;
      font-size: 20px;
      font-weight: 700;
      text-transform: uppercase;
      font-family: 'Montserrat', sans-serif;
    }

    /*HERO*/
    .hero {
      position: relative;
    }

    .hero img {}

    .hero .text {
      color: rgba(255, 255, 255, .8);
    }

    .hero .text h2 {
      color: #ffffff;
      font-size: 30px;
      margin-bottom: 0;
    }

    .hero .text h4 {
      color: #ffffff;
      font-size: 20px;
    }

    .hero .text h5 {
      color: #ffffff;
      font-size: 5px;
    }



    /*HEADING SECTION*/
    .heading-section {}

    .heading-section h2 {
      color: #000000;
      font-size: 28px;
      margin-top: 0;
      line-height: 1.4;
    }

    .heading-section .subheading {
      margin-bottom: 20px !important;
      display: inline-block;
      font-size: 13px;
      text-transform: uppercase;
      letter-spacing: 2px;
      color: rgba(0, 0, 0, .4);
      position: relative;
    }

    .heading-section .subheading::after {
      position: absolute;
      left: 0;
      right: 0;
      bottom: -10px;
      content: '';
      width: 100%;
      height: 2px;
      background: #f3a333;
      margin: 0 auto;
    }

    button {
      height: 20px;
      width: 100px;
      margin: -20px -50px;
      position: relative;
      top: 50%;
      left: 50%;
    }

    /* <ini card> */
    .column {
      float: left;
      width: 25%;
      padding: 0 10px;
    }

    /* Remove extra left and right margins, due to padding */
    .row {
      margin: 0 -5px;
    }

    /* Clear floats after the columns */
    .row:after {
      content: "";
      display: table;
      clear: both;
    }

    /* Responsive columns */
    @media screen and (max-width: 600px) {
      .column {
        width: 100%;
        display: block;
        margin-bottom: 20px;
      }
    }

    * {
      box-sizing: border-box;
    }

    body {
      font-family: Arial, Helvetica, sans-serif;
    }

    /* Style the counter cards */
    .card {
      box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
      padding: 3px;
      text-align: center;
      background-color: #F4D03F;
      margin: 0 auto;
      /* Added */
      float: none;
      /* Added */
      margin-bottom: 10px;
      /* Added */
    }

    /* <end card> */

    .heading-section-white {
      color: rgba(255, 255, 255, .8);
    }

    .heading-section-white h2 {
      font-size: 28px;
      font-family: 2;
      line-height: 1;
      padding-bottom: 0;
    }

    .yellow-square {
      background-color: #dcf0ef;
      width: 100px;
      height: 50px;
      margin: 0 auto;
      text-align: center;
    }

    .yellow-square .row {
      margin: 0 auto;
      left: 1em;
    }

    .topleft {
      position: absolute;
      top: 8px;
      left: 16px;
      font-size: 18px;
    }

    .heading-section-white h2 {
      color: #ffffff;
    }

    .heading-section-white .subheading {
      margin-bottom: 0;
      display: inline-block;
      font-size: 13px;
      text-transform: uppercase;
      letter-spacing: 2px;
      color: rgba(255, 255, 255, .4);
    }


    .icon {
      text-align: center;
    }

    .icon img {}


    /*SERVICES*/
    .text-services {
      padding: 10px 10px 0;
      text-align: center;
    }

    .text-services h3 {
      font-size: 20px;
    }

    /*BLOG*/
    .text-services .meta {
      text-transform: uppercase;
      font-size: 14px;
    }

    /*TESTIMONY*/
    .text-testimony .name {
      margin: 0;
    }

    .text-testimony .position {
      color: rgba(0, 0, 0, .3);

    }


    /*VIDEO*/
    .img {
      display: block;
      margin-left: auto;
      margin-right: auto;
    }



    .img .icon {
      position: absolute;
      top: 50%;
      left: 0;
      right: 0;
      bottom: 0;
      margin-top: -25px;
    }

    .img .icon a {
      display: block;
      width: 60px;
      position: absolute;
      top: 0;
      left: 50%;
      margin-left: -25px;
    }



    /*COUNTER*/
    .counter-text {
      text-align: center;
    }

    .counter-text .num {
      display: block;
      color: #ffffff;
      font-size: 34px;
      font-weight: 700;
    }

    .counter-text .name {
      display: block;
      color: rgba(255, 255, 255, .9);
      font-size: 13px;
    }


    /*FOOTER*/

    .footer {
      color: rgba(255, 255, 255, .5);

    }

    .footer .heading {
      color: #ffffff;
      font-size: 20px;
    }

    .footer ul {
      margin: 0;
      padding: 0;
    }

    .footer ul li {
      list-style: none;
      margin-bottom: 10px;
    }

    .footer ul li a {
      color: rgba(255, 255, 255, 1);
    }


    @media screen and (max-width: 500px) {

      .icon {
        text-align: left;
      }

      .text-services {
        padding-left: 0;
        padding-right: 20px;
        text-align: left;
      }

    }

  </style>


</head>

<body width="100%" style="margin: 0; padding: 0 !important; mso-line-height-rule: exactly; background-color: #222222;">

  <div
    style="display: none; font-size: 1px;max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden; mso-hide: all; font-family: sans-serif;">
    &zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;&zwnj;&nbsp;
  </div>
  <div style="max-width: 600px; margin: 0 auto;" class="email-container">
    <!-- BEGIN BODY -->
    <table align="center" role="presentation" cellspacing="0" cellpadding="0" border="0" width="100%"
      style="margin: auto;">
      <td></td>

      <tr>
        <td valign="middle" class="hero"
          style="background-image: url(https://raw.githubusercontent.com/rzlimam/psikotest/master/src/main/resources/img/bg_email.jpg); background-size: cover; height: 400px;">
          <table>
            <tr>
              <td>
                <div class="text" style="padding: 0 3em; text-align: center;">
                  <div class="topleft">
                  	<img src="https://raw.githubusercontent.com/rzlimam/psikotest/master/src/main/resources/img/lawencon_logo.png" />
                  </div>
                  <br>
                  <h4 style="text-align:left;">
                    <font size="6">
                    	Oops! ${name}
                    </font>
                  </h4>
                  <font size="3" color="white">
                    <p style="text-align:center;">
                      It's seem that you've forgotten your password
                    </p>
                    <p>
                       This is your new password
                    </p>
                  </font>
                </div>
                <h4 style="text-align:center;">
                    <font size="8" color="white">
                    	${password}
                    </font>
                  </h4>
              </td>
            </tr>
          </table>

          <font size="3" color="white">
            <p style="text-align:center;">
              Please use it wisely
            </p>
          </font>
        </td>

      </tr><!-- end tr -->
      <tr>
        <td class="bg_white">
          <table role="presentation" cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
              <td class="bg_dark email-section" style="text-align:center;">
                <div class="heading-section heading-section-white">
                  <p style="text-align:left;">
                    <i class="fa fa-building-o" style="font-size:15x"></i>
                    Wisma Staco, 5th Floor
                    Casablanca Street, Kav 18
                    12870, Jakarta Selatan</p>
                  <p>
                    <i class="fa fa-phone-square" style="font: size 25;px"></i>
                    (021) 837 02192</p>
                </div>
                <!-- <font color></font> -->
              </td>
            </tr><!-- end: tr -->
            <tr>
              <!-- kepake -->
          </table>
        </td>
      </tr><!-- end:tr -->
      <!-- 1 Column Text + Button : END -->
    </table>


  </div>
</body>

</html>
