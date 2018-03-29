/**nodejs 服务端**/
const http = require("http");
const url = require("url");
var mime = require('mime');
const httpProxy = require("http-proxy");
const fs = require("fs");

//nodejs服务器监听端口
const SERVER_PORT = 80;
//后台服务地址
const SERVICE_HOST = "127.0.0.1";
//后台服务监听端口
const SERVICE_PORT = 9090;

var proxy = httpProxy.createProxyServer({});

function testTransactionTarget(pathname,reg){
	return (new RegExp(reg)).test(pathname);
}
function send404(res){
	 res.writeHead(404, {
	        'Content-Type': 'text/plain'
	    });
	    res.write('Error 404: resource not found.');
	    res.end();
}

function sendFile(data,pathname,res){
	res.writeHead(200,{
		'Content-Type':mime.getType(pathname)
		});
	res.end(data);
}

function serveStatic(pathname,res){
	fs.readFile(pathname,function(err,data){
		if(err){
			console.log(err);
			send404(res);
		}else{
			sendFile(data,pathname,res);
		}
	});
}

const server = http.createServer(function(req,res){
	var pathname = url.parse(req.url).pathname;
	
	if(testTransactionTarget(pathname,"^/service/")){
		proxy.web(req, res,{
			target: {
				target:SERVICE_HOST,
				port:SERVICE_PORT
			} 
		});
		return;
	}
	
	if (pathname == '/') {
        filePath = 'views/index.html';
    } else if (pathname == '/login.html') {
        filePath = 'views/login.html';
    } else {
        filePath = pathname;
    }
    var absPath = './src/' + filePath;
    serveStatic(absPath,res);
});

server.listen(SERVER_PORT,function(){
	console.log("server listening on port:"+SERVER_PORT);
});