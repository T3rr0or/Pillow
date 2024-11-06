package rip.sunrise.client

import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.codec.LengthFieldBasedFrameDecoder
import io.netty.handler.codec.LengthFieldPrepender
import io.netty.handler.codec.compression.ZlibCodecFactory
import io.netty.handler.codec.compression.ZlibWrapper
import io.netty.handler.codec.serialization.ObjectDecoder
import io.netty.handler.ssl.SslContextBuilder
import io.netty.handler.ssl.util.InsecureTrustManagerFactory
import io.netty.handler.timeout.ReadTimeoutHandler
import rip.sunrise.packets.serialization.ObfuscatedClassResolver
import rip.sunrise.packets.serialization.ObfuscatedEncoder

fun main() {
    val group = NioEventLoopGroup()
    try {
        val bootstrap = Bootstrap()
        bootstrap.group(group)
            .channel(NioSocketChannel::class.java)
            .option(ChannelOption.SO_KEEPALIVE, true)
            .handler(ClientInitializer())

        println("Connecting to DreamBot servers.")
        val f = bootstrap.connect("cdn.dreambot.org", 43831).sync()

        f.channel().closeFuture().sync()
    } finally {
        group.shutdownGracefully()
    }
}

class ClientInitializer : ChannelInitializer<SocketChannel>() {
    override fun initChannel(ch: SocketChannel) {
        val pipeline = ch.pipeline()

        pipeline.addLast(SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build().newHandler(ch.alloc()))

        pipeline.addLast(ZlibCodecFactory.newZlibEncoder(ZlibWrapper.ZLIB));
        pipeline.addLast(ZlibCodecFactory.newZlibDecoder(ZlibWrapper.ZLIB));

        pipeline.addLast(LengthFieldBasedFrameDecoder(Int.MAX_VALUE, 0, 4, 0, 4))
        pipeline.addLast(LengthFieldPrepender(4))

        pipeline.addLast(ObfuscatedEncoder)
        pipeline.addLast(ObjectDecoder(ObfuscatedClassResolver))

        pipeline.addLast(ReadTimeoutHandler(600))

        pipeline.addLast(ClientHandler())
    }
}