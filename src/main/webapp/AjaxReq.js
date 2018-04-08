class MyAjax {
  constructor(url) {
    this.url = url
  }

  createcors(method, successfunc) {
    this.method = method
    let xhr = new XMLHttpRequest()
    if ('withCredentials' in xhr) {
      xhr.open(method, this.url, true)
    } else if (typeof XDomainRequest !== 'undefined') {
      xhr = new XDomainRequest()
      xhr.open(method, this.url)
    } else {
      xhr = null
    }
    this.xmlhttp = xhr
    this.xmlhttp.onreadystatechange = function () {
      if (this.readyState === 4 && this.status === 200) {
        if (successfunc === null || successfunc === undefined) {
          defsuccessfunc(this)
        } else {
          successfunc(this)
        }
      } else if(this.readyState === 4 && this.status !== 200) {
        deffailfunc(this)
      }
    }
  }

  openPost(successfunc) {
    this.createcors('POST', successfunc)
  }

  openGet(successfunc) {
    this.createcors('GET', successfunc)
  }

  send(form) {
    this.xmlhttp.send(form)
  }

  show() {
    console.log(this)
  }
}

var defsuccessfunc = function(xmlhttp) {
  console.log(xmlhttp.responseText)
}
var deffailfunc = function(xmlhttp) {
  console.log('readyState = ' +  xmlhttp.readyState + ', status = ' + xmlhttp.status)
}