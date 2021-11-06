function renderForm() {
    var html = "<div class='card'>";
    html += "<div class=\"card-body\">";
    html += "<h5 class=\"card-title\">Seja bem-vindo!</h5>";
    html += "<p class=\"card-text\">Você está conectado como: " + currentUser.displayName + "</p>";
    html += "<img class=\"card-img-top\" src=" + currentUser.images[0].url + " alt=\"Card image cap \" >";
    html += "</div>";
    html += "<ul class=\"list-group list-group-flush\">";
    html += "<li class=\"list-group-item\"><input type='text' placeholder='Insira o link da playlist' id='playlistURL'></li>";
    html += "<li class=\"list-group-item\"><input type='text' placeholder='Insira o nome que a playlist será salva' id='playlistName'></li>";
    html += "<li class=\"list-group-item\"><button type='button' onclick='handleSalvaPlaylist()'>Salvar</a></li>";
    html += "</ul>";
    html += "</div>";

    $('#main-form').html(html);
}

function renderPlaylistViewer(createdPlaylistID) {
    console.log(createdPlaylistID)
    let iframe = "<iframe src=\"https://open.spotify.com/embed/playlist/"+ createdPlaylistID +"?utm_source=generator\"";
    iframe += "width=\"300\"";
    iframe += "height=\"380\"";
    iframe += "frameBorder=\"0\"";
    iframe += "allow=\"autoplay; clipboard-write; encrypted-media\"></iframe>";

    $('#playlist-container').html(iframe);
}