<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include> 
<div class="container">
<h2>Foodizo!!!</h2>

<div id="myCarousel" class="carousel slide" data-ride="carousel">
<ol class="carousel-indicators">
<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
<li data-target="#myCarousel" data-slide-to="1" ></li>
<li data-target="#myCarousel" data-slide-to="2" ></li></ol>

<div class="carousel-inner">

<div class="item active">  
<img class="img-rounded"class="img-responsive centre-block" src="images/flow1.jpg" height="50px" style="width:100%" />
</div>

<div class="item">
<img class="img-rounded"class="img-responsive centre-block" src="images/flow2.jpg" style="width:100%" height="50px"/>
</div>

<div class="item">
<img class="img-rounded"class="img-responsive centre-block" src="images/flow3.jpg" style="width:100%" height="50px"/>
</div>
</div>


<a class="left carousel-control" href="#myCarousel"  data-slide="prev">  
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>  
      <span class="sr-only">Previous</span>  
    </a>  
    <a class="right carousel-control" href="#myCarousel" data-slide="next">  
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>  
      <span class="sr-only">Next</span>  
    </a>  

</div>
</div>

<br><br>
<div class="container">
  <div class="row">
    <div class="col-md-4">
      <div class="thumbnail">
      
        <a href="images/pizza.jpg" target="_blank">
          <img src="images/pizza.jpg" alt="Lights" style="width:100%">
          <div class="caption">
            <p>BAMBOO</p>
          </div>
        </a>
      </div>
    </div>
    <div class="col-md-4">
      <div class="thumbnail">
        <a href="images/download.png" target="_blank">
          <img src="images/download.png" alt="Nature" style="width:100%">
          <div class="caption">
            <p>Food Delivery on time</p>
          </div>
        </a>
      </div>
    </div>
    <div class="col-md-4">
      <div class="thumbnail">
        <a href="images/dessert.jpg" target="_blank">
          <img src="images/dessert.jpg" alt="Fjords" style="width:100%">
          <div class="caption">
            <p>Strawberry Pudding </p>
          </div>
        </a>
      </div>
    </div>
  </div>
</div>





<jsp:include page="/WEB-INF/views/footer.jsp"></jsp:include> 


</body>
</html>



