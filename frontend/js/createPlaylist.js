function handleSalvaPlaylist(){

    const getUrl = document.getElementById('playlistURL').value;
    const preID = getUrl.substring(getUrl.lastIndexOf('/') + 1);

    const id = preID.split('?')[0];
    const name = document.getElementById('playlistName').value;
    const user_id = currentUser.id;

    const playlistData = {id, name, user_id};

    salvaPlaylist(playlistData);

}

async function salvaPlaylist(playlistData) {

    let tracksIds = '';

    //tracksInfo[i].track.id
    const tracksInfo = await fetch('https://api.spotify.com/v1/playlists/' + playlistData.id + '/tracks', getOptions)
                            .then((response) => response.json())
                            .then((data) => { return data.items })

    tracksInfo.map((track) => {
        tracksIds = [...tracksIds, "spotify:track:" + track.track.id];
    })

    playlistData.uris = tracksIds;
    console.log(playlistData)

    const postOptions = {
        method: 'POST',
        mode: 'cors',
        headers: header,
        body: JSON.stringify(playlistData)
    };

    await fetch('http://localhost:8080/playlist/criaPlaylist/' + user.accessToken, postOptions)
        .then((response) => response.json())
        .then((response) => renderPlaylistViewer(response.id))

}