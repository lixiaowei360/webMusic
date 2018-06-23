function play(){
    alert("1");
}
$(document).ready(function(){
  var myPlaylist = new jPlayerPlaylist({
    jPlayer: "#jplayer_N",
    cssSelectorAncestor: "#jp_container_N"
  }, [
    {
      title:"晓伟歌曲",
      artist:"李晓伟",
      mp3:"http://127.0.0.1:8020/music/2018/03/16/741f0173-fa2b-4e2b-a11f-71d200efc9cb.mp3",
      poster: "/webMusic/images/m0.jpg"
    },
    {
      title:"Chucked Knuckles",
      artist:"3studios",
      mp3:"http://127.0.0.1:8020/music/2018/03/16/741f0173-fa2b-4e2b-a11f-71d200efc9cb.mp3",
      poster: "/webMusic/images/m0.jpg"
    }
  ], {
    playlistOptions: {
      enableRemoveControls: true,
      autoPlay: true
    },
    swfPath: "js/jPlayer",
    supplied: "webmv, ogv, m4v, oga, mp3",
    smoothPlayBar: true,
    keyEnabled: true,
    audioFullScreen: false
  });
    myPlaylist.add({
        title:"Your Face",
        artist:"The Stark Palace",
        mp3:"http://www.jplayer.org/audio/mp3/TSP-05-Your_face.mp3",
        oga:"http://www.jplayer.org/audio/ogg/TSP-05-Your_face.ogg",
        poster: "http://www.jplayer.org/audio/poster/The_Stark_Palace_640x360.png"
    });



  /*$(document).on('click', '.jp-play-me', function(e){
    alert("1");
    e && e.preventDefault();
    var $this = $(e.target);
    if (!$this.is('a')) $this = $this.closest('a');

    $('.jp-play-me').not($this).removeClass('active');
    $('.jp-play-me').parent('li').not($this.parent('li')).removeClass('active');

    $this.toggleClass('active');
    $this.parent('li').toggleClass('active');
    if( !$this.hasClass('active') ){
      myPlaylist.pause();
    }else{
      var i = Math.floor(Math.random() * (1 + 7 - 1));
      myPlaylist.play(i);
    }

  });*/



  // video

  $("#jplayer_1").jPlayer({
    ready: function () {
      $(this).jPlayer("setMedia", {
        title: "Big Buck Bunny",
        m4v: "http://flatfull.com/themes/assets/video/big_buck_bunny_trailer.m4v",
        ogv: "http://flatfull.com/themes/assets/video/big_buck_bunny_trailer.ogv",
        webmv: "http://flatfull.com/themes/assets/video/big_buck_bunny_trailer.webm",
        poster: "images/m41.jpg"
      });
    },
    swfPath: "js",
    supplied: "webmv, ogv, m4v",
    size: {
      width: "100%",
      height: "auto",
      cssClass: "jp-video-360p"
    },
    globalVolume: true,
    smoothPlayBar: true,
    keyEnabled: true
  });

});