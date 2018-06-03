package com.other.soa.server;

import org.jboss.netty.channel.*;

/**
 * Created by pengyunlong on 2018/6/2.
 */
public class ServerLogicHandler extends SimpleChannelHandler {


    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        System.out.println("######channelConnected");
        //channel group is thread-safe
        Server.channelGroup.add(e.getChannel());
        System.out.println(e.getChannel().toString());
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        System.out.println("#####messageRecived");
        //经过ServerReadDecoder的处理，这里可以直接得到String类型的message
        String msg = (String) e.getMessage();
        System.out.println("The message sent by client is :"+ msg);
        Channel channel = e.getChannel();
        String str = "Hi,Client.";
        //由于IO操作是异步的，当方法返回时并不能保证IO操作一定完成了
        //因此返回一个ChannelFuture对象的实例
        //该实例中保存了IO操作的状态信息
        ChannelFuture channelFuture = channel.write(str);
        //为ChannelFuture对象实例添加监听，如果数据发送完毕则关闭连接
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                Channel ch = channelFuture.getChannel();
                ch.close();
            }
        });
        System.out.println("The message has sent to client.");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        e.getCause().printStackTrace();
        Channel channel = e.getChannel();
        channel.close();
    }
}
