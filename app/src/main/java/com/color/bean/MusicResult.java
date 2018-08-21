package com.color.bean;

import java.util.List;

/**
 * Created by wanggaolei on 2018/8/21.
 */

public class MusicResult {

    /**
     * status : 1
     * err_code : 0
     * data : {"hash":"3C3D93A5615FB42486CAB22024945264","timelength":216000,"filesize":3449934,"audio_name":"周杰伦 - 告白气球","have_album":1,"album_name":"周杰伦的床边故事","album_id":"1645030","img":"http://imge.kugou.com/stdmusic/20160623/20160623233610830051.jpg","have_mv":1,"video_id":"589001","author_name":"周杰伦","song_name":"告白气球","lyrics":"[00:00.17]周杰伦 - 告白气球\r\n[00:01.69]作词：方文山\r\n[00:02.86]作曲：周杰伦\r\n[00:23.60]塞纳河畔 左岸的咖啡\r\n[00:26.35]我手一杯 品尝你的美\r\n[00:29.52]留下唇印的嘴\r\n[00:34.30]花店玫瑰 名字写错谁\r\n[00:36.94]告白气球 风吹到对街\r\n[00:40.11]微笑在天上飞\r\n[00:44.11]你说你有点难追 想让我知难而退\r\n[00:49.28]礼物不需挑最贵 只要香榭的落叶\r\n[00:54.81]喔 营造浪漫的约会\r\n[00:57.28]不害怕搞砸一切\r\n[00:59.86]拥有你就拥有 全世界\r\n[01:05.13]亲爱的 爱上你 从那天起\r\n[01:11.17]甜蜜的很轻易\r\n[01:15.84]亲爱的 别任性 你的眼睛\r\n[01:22.02]在说我愿意\r\n[01:49.00]塞纳河畔 左岸的咖啡\r\n[01:51.59]我手一杯 品尝你的美\r\n[01:54.76]留下唇印的嘴\r\n[01:59.65]花店玫瑰 名字写错谁\r\n[02:02.38]告白气球 风吹到对街\r\n[02:05.43]微笑在天上飞\r\n[02:09.54]你说你有点难追\r\n[02:11.87]想让我知难而退\r\n[02:14.55]礼物不需挑最贵\r\n[02:17.52]只要香榭的落叶\r\n[02:19.82]喔 营造浪漫的约会\r\n[02:22.61]不害怕搞砸一切\r\n[02:25.25]拥有你就拥有 全世界\r\n[02:30.13]亲爱的 爱上你 从那天起\r\n[02:36.53]甜蜜的很轻易\r\n[02:40.83]亲爱的 别任性 你的眼睛\r\n[02:47.32]在说我愿意\r\n[02:51.57]亲爱的 爱上你 恋爱日记\r\n[02:57.95]飘香水的回忆\r\n[03:02.26]一整瓶 的梦境 全都有你\r\n[03:08.70]搅拌在一起\r\n[03:12.99]亲爱的别任性 你的眼睛\r\n[03:21.25]在说我愿意\r\n","author_id":"3520","privilege":8,"privilege2":"1000","play_url":"http://fs.w.kugou.com/201808211457/054c01ca76b8f9f4927d89ba258f9298/G059/M02/17/1D/ew0DAFdr9KmAf5GnADSkTnjFCm0437.mp3","authors":[{"is_publish":"1","author_id":"3520","avatar":"20180515002522714.jpg","author_name":"周杰伦"}],"bitrate":128}
     */

    private int status;
    private int err_code;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getErr_code() {
        return err_code;
    }

    public void setErr_code(int err_code) {
        this.err_code = err_code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * hash : 3C3D93A5615FB42486CAB22024945264
         * timelength : 216000
         * filesize : 3449934
         * audio_name : 周杰伦 - 告白气球
         * have_album : 1
         * album_name : 周杰伦的床边故事
         * album_id : 1645030
         * img : http://imge.kugou.com/stdmusic/20160623/20160623233610830051.jpg
         * have_mv : 1
         * video_id : 589001
         * author_name : 周杰伦
         * song_name : 告白气球
         * lyrics : [00:00.17]周杰伦 - 告白气球
         [00:01.69]作词：方文山
         [00:02.86]作曲：周杰伦
         [00:23.60]塞纳河畔 左岸的咖啡
         [00:26.35]我手一杯 品尝你的美
         [00:29.52]留下唇印的嘴
         [00:34.30]花店玫瑰 名字写错谁
         [00:36.94]告白气球 风吹到对街
         [00:40.11]微笑在天上飞
         [00:44.11]你说你有点难追 想让我知难而退
         [00:49.28]礼物不需挑最贵 只要香榭的落叶
         [00:54.81]喔 营造浪漫的约会
         [00:57.28]不害怕搞砸一切
         [00:59.86]拥有你就拥有 全世界
         [01:05.13]亲爱的 爱上你 从那天起
         [01:11.17]甜蜜的很轻易
         [01:15.84]亲爱的 别任性 你的眼睛
         [01:22.02]在说我愿意
         [01:49.00]塞纳河畔 左岸的咖啡
         [01:51.59]我手一杯 品尝你的美
         [01:54.76]留下唇印的嘴
         [01:59.65]花店玫瑰 名字写错谁
         [02:02.38]告白气球 风吹到对街
         [02:05.43]微笑在天上飞
         [02:09.54]你说你有点难追
         [02:11.87]想让我知难而退
         [02:14.55]礼物不需挑最贵
         [02:17.52]只要香榭的落叶
         [02:19.82]喔 营造浪漫的约会
         [02:22.61]不害怕搞砸一切
         [02:25.25]拥有你就拥有 全世界
         [02:30.13]亲爱的 爱上你 从那天起
         [02:36.53]甜蜜的很轻易
         [02:40.83]亲爱的 别任性 你的眼睛
         [02:47.32]在说我愿意
         [02:51.57]亲爱的 爱上你 恋爱日记
         [02:57.95]飘香水的回忆
         [03:02.26]一整瓶 的梦境 全都有你
         [03:08.70]搅拌在一起
         [03:12.99]亲爱的别任性 你的眼睛
         [03:21.25]在说我愿意

         * author_id : 3520
         * privilege : 8
         * privilege2 : 1000
         * play_url : http://fs.w.kugou.com/201808211457/054c01ca76b8f9f4927d89ba258f9298/G059/M02/17/1D/ew0DAFdr9KmAf5GnADSkTnjFCm0437.mp3
         * authors : [{"is_publish":"1","author_id":"3520","avatar":"20180515002522714.jpg","author_name":"周杰伦"}]
         * bitrate : 128
         */

        private String hash;
        private int timelength;
        private int filesize;
        private String audio_name;
        private int have_album;
        private String album_name;
        private String album_id;
        private String img;
        private int have_mv;
        private String video_id;
        private String author_name;
        private String song_name;
        private String lyrics;
        private String author_id;
        private int privilege;
        private String privilege2;
        private String play_url;
        private int bitrate;
        private List<AuthorsBean> authors;

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public int getTimelength() {
            return timelength;
        }

        public void setTimelength(int timelength) {
            this.timelength = timelength;
        }

        public int getFilesize() {
            return filesize;
        }

        public void setFilesize(int filesize) {
            this.filesize = filesize;
        }

        public String getAudio_name() {
            return audio_name;
        }

        public void setAudio_name(String audio_name) {
            this.audio_name = audio_name;
        }

        public int getHave_album() {
            return have_album;
        }

        public void setHave_album(int have_album) {
            this.have_album = have_album;
        }

        public String getAlbum_name() {
            return album_name;
        }

        public void setAlbum_name(String album_name) {
            this.album_name = album_name;
        }

        public String getAlbum_id() {
            return album_id;
        }

        public void setAlbum_id(String album_id) {
            this.album_id = album_id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getHave_mv() {
            return have_mv;
        }

        public void setHave_mv(int have_mv) {
            this.have_mv = have_mv;
        }

        public String getVideo_id() {
            return video_id;
        }

        public void setVideo_id(String video_id) {
            this.video_id = video_id;
        }

        public String getAuthor_name() {
            return author_name;
        }

        public void setAuthor_name(String author_name) {
            this.author_name = author_name;
        }

        public String getSong_name() {
            return song_name;
        }

        public void setSong_name(String song_name) {
            this.song_name = song_name;
        }

        public String getLyrics() {
            return lyrics;
        }

        public void setLyrics(String lyrics) {
            this.lyrics = lyrics;
        }

        public String getAuthor_id() {
            return author_id;
        }

        public void setAuthor_id(String author_id) {
            this.author_id = author_id;
        }

        public int getPrivilege() {
            return privilege;
        }

        public void setPrivilege(int privilege) {
            this.privilege = privilege;
        }

        public String getPrivilege2() {
            return privilege2;
        }

        public void setPrivilege2(String privilege2) {
            this.privilege2 = privilege2;
        }

        public String getPlay_url() {
            return play_url;
        }

        public void setPlay_url(String play_url) {
            this.play_url = play_url;
        }

        public int getBitrate() {
            return bitrate;
        }

        public void setBitrate(int bitrate) {
            this.bitrate = bitrate;
        }

        public List<AuthorsBean> getAuthors() {
            return authors;
        }

        public void setAuthors(List<AuthorsBean> authors) {
            this.authors = authors;
        }

        public static class AuthorsBean {
            /**
             * is_publish : 1
             * author_id : 3520
             * avatar : 20180515002522714.jpg
             * author_name : 周杰伦
             */

            private String is_publish;
            private String author_id;
            private String avatar;
            private String author_name;

            public String getIs_publish() {
                return is_publish;
            }

            public void setIs_publish(String is_publish) {
                this.is_publish = is_publish;
            }

            public String getAuthor_id() {
                return author_id;
            }

            public void setAuthor_id(String author_id) {
                this.author_id = author_id;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getAuthor_name() {
                return author_name;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }
        }
    }
}
