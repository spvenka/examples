// The root URL for the RESTful services
var rootURL = "http://localhost:8080/examples/rest/persons";

var currentPerson;

// Retrieve Person list when application starts 
findAll();

// Nothing to delete in initial application state
$('#btnDelete').hide();

// Register listeners
$('#btnSearch').click(function() {
	search($('#searchKey').val());
	return false;
});

// Trigger search when pressing 'Return' on search key input field
$('#searchKey').keypress(function(e){
	if(e.which == 13) {
		search($('#searchKey').val());
		e.preventDefault();
		return false;
    }
});

$('#btnAdd').click(function() {
	newPerson();
	return false;
});

$('#btnSave').click(function() {
	if ($('#personId').val() == '')
		addPerson();
	else
		updatePerson();
	return false;
});

$('#btnDelete').click(function() {
	deletePerson();
	return false;
});

$('#personList a').live('click', function() {
	findById($(this).data('identity'));
});

// Replace broken images with generic person bottle
$("img").error(function(){
  $(this).attr("src", "pics/generic.jpg");

});

function search(searchKey) {
	if (searchKey == '') 
		findAll();
	else
		findByName(searchKey);
}

function newPerson() {
	$('#btnDelete').hide();
	currentPerson = {};
	renderDetails(currentPerson); // Display empty form
}

function findAll() {
	console.log('findAll');
	$.ajax({
		type: 'GET',
		url: rootURL,
		dataType: "json", // data type of response
		//success: renderList,
		success: function(data){
			console.log('findAll success: ' + data);
			renderList(data);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('findAll error: ' + errorThrown);
		}
	});
}

function findByName(searchKey) {
	console.log('findByName: ' + searchKey);
	$.ajax({
		type: 'GET',
		url: rootURL + '/search/' + searchKey,
		dataType: "json",
		//success: renderList,
		success: function(data){
			currentPerson = {};
			renderDetails(currentPerson);
			console.log('findByName success: ' + data);
			renderList(data);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('findByName error: ' + errorThrown);
		}
	});
}

function findById(personId) {
	console.log('findById: ' + personId);
	$.ajax({
		type: 'GET',
		url: rootURL + '/' + personId,
		dataType: "json",
		success: function(data){
			$('#btnDelete').show();
			console.log('findById success: ' + data.name);
			currentPerson = data;
			renderDetails(currentPerson);
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('findById error: ' + errorThrown);
		}
	});
}

function addPerson() {
	console.log('addPerson');
	$.ajax({
		type: 'POST',
		contentType: 'application/json',
		url: rootURL,
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('Person created successfully');
			$('#btnDelete').show();
			$('#personId').val(data.personId);
			$('#personList').append('<li id="'+ data.personId + '"><a href="#" data-identity="' + data.personId + '">'+data.name+'</a></li>');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('addPerson error: ' + errorThrown);
		}
	});
}

function updatePerson() {
	console.log('updatePerson');
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url: rootURL + '/' + $('#personId').val(),
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('Person updated successfully');
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('updatePerson error: ' + errorThrown);
		}
	});
}

function deletePerson() {
	console.log('deletePerson');
	$.ajax({
		type: 'DELETE',
		contentType: 'application/json',
		url: rootURL + '/' + $('#personId').val(),
		dataType: "json",
		data: formToJSON(),
		success: function(data, textStatus, jqXHR){
			alert('Person deleted successfully');
			currentPerson = {};
			renderDetails(currentPerson);
			$("#"+data.personId).remove();
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('deletePerson error: ' + errorThrown);
		}
	});
}

function renderList(data) {
	// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
	var list = data == null ? [] : (data.person instanceof Array ? data.person : data);

	$('#personList li').remove();
	$.each(list, function(index, person) {
		$('#personList').append('<li id="'+ person.personId + '"><a href="#" data-identity="' + person.personId + '">'+person.name+'</a></li>');
	});
}

function renderDetails(person) {
	$('#personId').val(person.personId);
	$('#name').val(person.name);
	$('#age').val(person.age);
	$('#homeTown').val(person.homeTown);
	//$('#pic').attr('src', 'pics/' + person.picture);
	//$('#description').val(wine.description);
}

// Helper function to serialize all the form fields into a JSON string
function formToJSON() {
	var personId = $('#personId').val();
	if(personId == ""){
		return JSON.stringify({ 
			"name": $('#name').val(), 
			"age": $('#age').val(),
			"homeTown": $('#homeTown').val(),
			"picture": currentPerson.picture,
			//"description": $('#description').val()
			});
	}else{
		return JSON.stringify({
			"personId": personId == "" ? null : personId, 
			"name": $('#name').val(), 
			"age": $('#age').val(),
			"homeTown": $('#homeTown').val(),
			"picture": currentPerson.picture,
			//"description": $('#description').val()
			});
	}
}
