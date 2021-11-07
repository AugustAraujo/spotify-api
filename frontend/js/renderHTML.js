function renderForm() {

    var html = "<div class='login'>"
    html += "<div class='imagem'>"
    html += "<img src=" + currentUser.images[0].url + " alt=Imagem de perfil' class='usuario' width='100' height='100'>";
    html += "</div>"
    html += "<h1>" + currentUser.displayName + "</h1>"
    html += "<form>";

    html += "<input type='text' placeholder='Insira o nome da playlist' id='playlistName'>";

    html += "<input type='text' placeholder='Insira o link da playlist' id='playlistURL'>";

    html += "<button type='button' id='enviar' onclick='handleSalvaPlaylist()'> Salvar </button>"

    html += "</form>";
    html += "</div>"

    $('#main-form').html(html);
}

function renderPlaylistViewer(createdPlaylistID) {
    console.log(createdPlaylistID)
    let iframe = "<iframe src=\"https://open.spotify.com/embed/playlist/"+ createdPlaylistID +"?utm_source=generator\"";
    iframe += "width=\"400\"";
    iframe += "height=\"515\"";
    iframe += "frameBorder=\"0\"";
    iframe += "allow=\"autoplay; clipboard-write; encrypted-media\"></iframe>";

    $('#playlist-container').html(iframe);
}