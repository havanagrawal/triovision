var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/triovision');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        
        $.ajax({
            url: 'player',
            type: 'POST',
            success: function(data, status) { 
            	console.log('POST completed');
            	console.log(data);
            	console.log(status);
            	
            	var playerId = data.playerId;
            	
            	console.log("Assigned player id: " + playerId);
            	
            	stompClient.subscribe('/topic/player-' + playerId, function (gameState) {
            		console.log("Got message: " + gameState);
            		console.log("JSON Content: " + gameState.body);
            		showGameState(gameState.body);
                    //showGreeting(JSON.parse(greeting.body).board);
                });
            }
        });
        
        
        
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/player", {user: 'havana'}, "Hello!");
}

function showGameState(gameState) {
    $("#gameState").append("<tr><td>" + gameState + "</td></tr>");
    
    var gameStateJson = JSON.parse(gameState);
    
    $("#gameState").append(makeGameStateTable(gameStateJson));
    $("#gameState").append(getCards(gameStateJson.openCards));
}

function makeGameStateTable(gameState) {
	
	var htmlTableBoard = "";
	
	for (var i = 0; i < 4; i++) {
		htmlTableBoard = htmlTableBoard + "<tr>";
		for (var j = 0; j < 4; j++) {
			htmlTableBoard = htmlTableBoard + "<td>" + gameState.board.board[i][j] + "</td>";			
		}
		htmlTableBoard = htmlTableBoard + "</tr>";
	}
	
	return '<table border="1">' + htmlTableBoard + "</table>";
}

function getCards(cards) {
	var htmlCards = "";
	
	for (var i = 0; i < cards.length; i++) {
		var card = getCardTable(cards[i]["cardInfo"]);
		htmlCards = htmlCards + "<td>" + card + "</td>";  
	}
	
	return '<table border="1">' + htmlCards + "</table>";
}

function getCardTable(card) {
	var htmlCard = "";
	
	for (var i = 0; i < 3; i++) {
		htmlCard = htmlCard + "<tr>";
		for (var j = 0; j < 2; j++) {
			htmlCard = htmlCard + "<td>" + card[i][j] + "</td>";			
		}
		htmlCard = htmlCard + "</tr>";
	}
	
	return '<table border="1">' + htmlCard + "</table>";
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});