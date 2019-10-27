var app = new Vue({
    el: '#loginApp',
    data: {
        username: '',
        password: '',
        code: '',
        inputCode: ''
    },
    methods: {
        createCode: function () {
            var code = '';
            var codeLength = 4;
            var random = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C', 'D',
                'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
                'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'];
            for (var i = 0; i < codeLength; i++) {
                var index = Math.floor(Math.random() * 36);
                code += random[index];
            }
            this.code = code;
        },
        toLogin: function () {
            var that = this;
            var param = {
                'loginName': that.username,
                'password': that.password
            };
            axios({
                method: 'POST',
                url: '/login',
                data: param
            }).then(function (res) {
                console.log(res.data);
            }).catch(function (err) {
                console.log(err);
            });

        },
        resetParams: function () {
            this.username = '';
            this.password = '';
            this.inputCode = '';
            this.createCode();
        }
    }
})