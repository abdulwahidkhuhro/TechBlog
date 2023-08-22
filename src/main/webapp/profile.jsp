
<%@page import="com.tech.blog.entities.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tech.blog.helper.ConnectionProvider"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.tech.blog.dao.PostDao"%>
<%@page import="com.tech.blog.entities.Message"%>
<%@page import="com.tech.blog.entities.User"%>
<%@page errorPage="error_page.jsp"%>
<%
User user = (User) session.getAttribute("currentUser");

if (user == null) {
	response.sendRedirect("login_page.jsp");
}
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style type="text/css">
.banner-background {
	clip-path: polygon(30% 0%, 70% 0%, 100% 0, 100% 96%, 75% 87%, 29% 98%, 0 88%, 0 0);
}
</style>
</head>
<body>
	<!-- navbar -->

	<nav class="navbar navbar-expand-lg navbar-dark primary-background">
		<a class="navbar-brand" href="index.jsp"><span
			class="fa fa-certificate"></span>Tech Blog</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="#"><span
						class="fa fa-code"></span>Learn Code With Durgesh <span
						class="sr-only">(current)</span> </a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"><span class="fa fa-check"></span>
						Categories </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="#">Programming Language</a> <a
							class="dropdown-item" href="#">Project Management</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="#">Something else here</a>
					</div></li>
				<li class="nav-item"><a class="nav-link" href="#"><span
						class="fa fa-address-book-o"></span> Contact</a></li>

				<li class="nav-item"><a class="nav-link" href="#"
					data-toggle="modal" data-target="#add-post-modal"><span
						class="fa fa-share-square"></span> Post</a></li>



			</ul>
			<ul class="navbar-nav mr-right">
				<li class="nav-item"><a class="nav-link" href="#!"
					data-toggle="modal" data-target="#profile-modal"><span
						class="fa fa-user-circle"></span> <%=user.getName()%> </a></li>

				<li class="nav-item"><a class="nav-link" href="logout"><span
						class="fa fa-user-plus"></span> Logout</a></li>

			</ul>
		</div>
	</nav>

	<!-- end of navbar -->

	<%
	Message message = (Message) session.getAttribute("message");

	if (message != null) {
	%>

	<div class="alert <%=message.getCssClass()%>" role="alert">
		<%=message.getContent()%>
	</div>

	<%
	session.removeAttribute("message");
	}
	%>


	<!-- profile model -->


	<!-- Modal -->
	<div class="modal fade" id="profile-modal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header primary-background text-white">
					<h5 class="modal-title" id="exampleModalLabel">TechBlog</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="container text-center">
						<img alt="profile image" src="pics/<%=user.getProfile()%>"
							class="img-fluid" width="100px" height="100px"
							style="border: 1px solid #D3D3D3; border-radius: 50%">
						<h5 class="modal-title"><%=user.getName()%></h5>
						<!--  details -->

						<div id="profile-details">
							<table class="table">
								<thead>
									<tr>
										<th scope="col">ID :</th>
										<th scope="col"><%=user.getId()%></th>

									</tr>
								</thead>
								<tbody>
									<tr>
										<th scope="row">Email :</th>
										<td><%=user.getEmail()%></td>
									</tr>
									<tr>
										<th scope="row">Gender :</th>
										<td><%=user.getGender()%></td>
									</tr>
									<tr>
										<th scope="row">Status :</th>
										<td><%=user.getAbout()%></td>
									</tr>

									<tr>
										<th scope="row">Registered on :</th>
										<td><%=user.getRegistrationTime()%></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div id="profile-edit" style="display: none;">
							<h3>Edit</h3>

							<form action="edit" method="POST" enctype="multipart/form-data">
								<table class="table">
									<thead>
										<tr>
											<th scope="col">ID :</th>
											<th scope="col"><%=user.getId()%></th>
										</tr>
									</thead>

									<tbody>
										<tr>
											<th>Name</th>
											<td><input type="text" class="form-control"
												name="user-name" value="<%=user.getName()%>" /></td>
										</tr>
										<tr>
											<th>Email</th>
											<td><input type="email" class="form-control"
												name="user-email" value="<%=user.getEmail()%>" /></td>
										</tr>

										<tr>
											<th>Password</th>
											<td><input type="password" class="form-control"
												name="user-password" value="<%=user.getPassword()%>" /></td>
										</tr>

										<tr>
											<th>Gender</th>
											<td><%=user.getGender()%></td>
										</tr>

										<tr>
											<th>About</th>
											<td><textarea name="about-user" class="form-control"
													rows="5" cols="20"><%=user.getAbout()%></textarea></td>
										</tr>

										<tr>
											<th>Choose Pic</th>
											<td><input type="file" class="form-control"
												name="choose-new-profile-pic" /></td>
										</tr>

									</tbody>

								</table>

								<div class="container">
									<button type="submit" class="btn btn-outline-primary">Save</button>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button id="profile-close-btn" type="button"
						class="btn btn-secondary" data-dismiss="modal">Close</button>
					<button id="edit-profile-btn" type="button" class="btn btn-primary">Edit</button>
				</div>
			</div>
		</div>
	</div>


	<!-- end of profile model -->

	<!-- post modal -->


	<!-- Modal -->
	<div class="modal fade" id="add-post-modal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Provide Post
						details</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form id="add-post-form" action="post" method="post">
					
						<div class="form-group">
							<select class="form-control" name="id">
								<option selected="selected" disabled="disabled">Select Category</option>
								<%
									PostDao postDao = new PostDao(ConnectionProvider.getConnection());
								ArrayList<Category> list = postDao.getAllCategories();
								
								for(Category category : list){
								%>
								<option value="<%= category.getId()%>"><%= category.getName() %></option>
								<%
								}
								%>
							</select>
						</div>
						<div class="form-group">
							<input name="post-title" type="text" placeholder="Enter post title"
								class="form-control" />
						</div>

						<div class="form-group">
							<textarea name="post-content" class="form-control" placeholder="Enter your content"
								style="height: 150px"></textarea>
						</div>

						<div class="form-group">
							<textarea name="post-code" class="form-control"
								placeholder="Enter your program (if any)" style="height: 150px"></textarea>
						</div>

						<div class="form-group">
							<label>Select pic for post</label><br /> <input type="file" name="post-image" />
						</div>
						
						<div class="container text-center">
							<button type="submit" class="btn btn-outline-primary" >Post</button>
						</div>
					</form>
				</div>
				
			</div>
		</div>
	</div>
	<!-- end of post modal -->


	<!-- javascript -->
	<!--javascript -->
	<script src="https://code.jquery.com/jquery-3.7.0.min.js"
		integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g="
		crossorigin="anonymous"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>

	<script src="js/index.js" type="text/javascript"></script>
	<script>
		$(document).ready(function() {

			let editStatus = false;

			$("#edit-profile-btn").click(function() {

				if (editStatus) {
					$("#profile-details").show();
					$("#profile-edit").hide();
					$(this).text("Edit");
					editStatus = false

				} else {
					$("#profile-details").hide();
					$("#profile-edit").show();
					$(this).text("Back");
					editStatus = true
				}

			});

		});
	</script>
	
	<!-- now post js -->
	
	<script>
		$(document).ready(function (e){
			//
			$("add-post-form").on("submit",function(event){
				//this code get called when form is submitted...
				event.preventDefault();
				
				console.log("you have clicked on submit...");
				let form = new FormData(this);
				
				//now requesting to server
				console.log("posted")
				$.ajax({
					url: "post",
					type: "POST",
					data: form,
					success: function(data, textStatus, jqXHR){
						//success
						console.log(data)
					},
					error: function(jqXHR, textStatus, errorThrown){
						//error
					},
					processData: false,
					contentType: false,
				});
			});
		});
	</script>
	
</body>
</html>