var body = document.getElementById('body')
var conb = document.getElementById('conb')
var int = document.getElementById('int')
var bt = document.getElementById('bt')
var p = document.getElementById('panel')
var pt = document.getElementById('ptitle')
var ws

function onload() {
    console.log('onload');
    body.style.height = getWindowH() + 'px'
    int.placeholder = '请输入正确的Key'
}

function connect() {
    let key = int.value
    if (key !== '') {
        let url = 'http://localhost:8080/barrage/connect?key=' + key
        let ajax = new MyAjax(url)
        ajax.openGet(function (xmlhttp) {
            let code = xmlhttp.responseText
            let json = JSON.parse(code)
            if (json.code === 1) {
                bt.disabled = true
                removeClass(p, 'panel-default')
                removeClass(p, 'panel-danger')
                addClass(p, 'panel-success')
                removeClass(p, 'pvi')
                addClass(p, 'phi')
                pt.innerHTML = '连接成功!'

                setTimeout(() => {
                    removeClass(p, 'phi')
                    addClass(p, 'pvi')
                    bt.disabled = null
                }, 1200);
                wsconnect(key)
            } else {
                bt.disabled = true
                removeClass(p, 'panel-default')
                removeClass(p, 'panel-success')
                addClass(p, 'panel-danger')
                removeClass(p, 'pvi')
                addClass(p, 'phi')
                pt.innerHTML = '连接失败!会议已经过期!'

                setTimeout(() => {
                    removeClass(p, 'phi')
                    addClass(p, 'pvi')
                    bt.disabled = null
                }, 1200);
            }
        })
        ajax.send()
    } else {
        bt.disabled = true
        removeClass(p, 'panel-danger')
        removeClass(p, 'panel-success')
        removeClass(p, 'pvi')
        addClass(p, 'phi')
        pt.innerHTML = '无效的Key'

        setTimeout(() => {
            removeClass(p, 'phi')
            addClass(p, 'pvi')
            bt.disabled = null
        }, 1200);
    }
}

function wsconnect(key) {
    let barrageTop = getWindowH(0.2)
    let barrageButton = getWindowH(0.8)
    ws = new WebSocket('ws://localhost:5999')
    ws.onmessage = function (event) {
        text = event.data
        let ojbk = JSON.parse(text)
        let msg = ojbk.msg
        if (ojbk.msgType === 101) {
            let item={
                info:msg, //文字 
                href:'JavaScript:void(0)', //链接 
                close:true, //显示关闭按钮 
                speed:18, //延迟,单位秒,默认6 
                bottom: RandomNumBoth(barrageTop, barrageButton),
                color:'#fff', //颜色,默认白色 
              }
             $('body').barrager(item);
        }
    }
    ws.onclose = function (event) {

    }
    ws.onopen = function (event) {
        changeClass(bt, 'btn btn-xs btn-primary')
        changeClass(bt, 'btn btn-xs btn-success')
        setTimeout(() => {
            changeClass(conb, 'vi')
            changeClass(conb, 'hi')
        }, 1000);
        var json = '{"msgType" : "1", "msgBody":{"loginUserId" : "' + key + '"}}'
        ws.send(json)
    }

    ws.onerror = function (event) {
        changeClass(bt, 'btn btn-xs btn-primary')
        changeClass(bt, 'btn btn-xs btn-danger')
    }
}