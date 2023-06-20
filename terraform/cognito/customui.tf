resource "aws_cognito_user_pool_ui_customization" "snapagogocustom" {
  user_pool_id = aws_cognito_user_pool.userpool.id
  image_file = filebase64(var.cognito_image)
  css                = <<-EOT
          .logo-customizable {
          	max-width: 90%;
          	max-height: 90%;
          }
          .banner-customizable {
          	padding: 25px 0px 25px 0px;
          	background-color: lightgray;
          }
          .label-customizable {
          	font-weight: 400;
          }
          .textDescription-customizable {
          	padding-top: 10px;
          	padding-bottom: 10px;
          	display: block;
          	font-size: 16px;
          }
          .idpDescription-customizable {
          	padding-top: 10px;
          	padding-bottom: 10px;
          	display: none;
          	font-size: 16px;
          }
          .legalText-customizable {
          	color: #747474;
          	font-size: 11px;
          }
          .submitButton-customizable {
          	font-size: 14px;
          	font-weight: bold;
          	margin: 20px 0px 10px 0px;
          	height: 40px;
          	width: 100%;
          	color: #fff;
          	background-color: #337ab7;
          }
          .submitButton-customizable:hover {
          	color: #fff;
          	background-color: #286090;
          }
          .errorMessage-customizable {
          	padding: 5px;
          	font-size: 14px;
          	width: 100%;
          	background: #F5F5F5;
          	border: 2px solid #D64958;
          	color: #D64958;
          }
          .inputField-customizable {
          	width: 100%;
          	height: 34px;
          	color: #555;
          	background-color: #fff;
          	border: 1px solid #ccc;
          }
          .inputField-customizable:focus {
          	border-color: #66afe9;
          	outline: 0;
          }
          .idpButton-customizable {
          	height: 40px;
          	width: 100%;
          	width: 100%;
          	text-align: center;
          	margin-bottom: 15px;
          	color: #fff;
          	background-color: #5bc0de;
          	border-color: #46b8da;
          }
          .idpButton-customizable:hover {
          	color: #fff;
          	background-color: #31b0d5;
          }
          .socialButton-customizable {
          	border-radius: 2px;
          	height: 40px;
          	margin-bottom: 15px;
          	padding: 1px;
          	text-align: left;
          	width: 100%;
          }
          .redirect-customizable {
          	text-align: center;
          }
          .passwordCheck-notValid-customizable {
          	color: #DF3312;
          }
          .passwordCheck-valid-customizable {
          	color: #19BF00;
          }
          .background-customizable {
          	background-color: #fff;
          }
        EOT
}
