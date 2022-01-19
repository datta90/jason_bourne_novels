object Demo {
    import Mail._

    def main(args: Array[String] )
    {

        printf("=======hi======")

        send a Mail(
            from = ("", "Dattatreya"),
//            from = ("xxx", "Dattatreya"),
//            from = ("x@yx.com", "Dattatreya"),
            to = Seq("xxx","xx"),
            cc = Seq("xxzzaa"),

            subject  = "prev tata sky broadband bill 2020",
            message = "PFA \n enjoy  ....\n  --regards\n  Dattatreya",
            //    richMessage = "Here's the <blink>latest</blink> <strong>Strategy</strong>...",
            //    attachments = Seq(new java.io.File("/home/datta/Downloads/AI_0865_20201118_boardingPass.pdf")
            //  ,new java.io.File("/home/datta/Downloads/AI_0809_20201113_boardingPass.pdf") ) )


            attachments = Seq(
                new java.io.File(""))

        )


    }
}

//  send a new Mail (
//    from = "john.smith@mycompany.com" -> "John Smith",
//    to = Seq("dev@mycompany.com", "marketing@mycompany.com"),
//    subject = "Our New Strategy (tm)",
//    message = "Please find attach the latest strategy document.",
//    richMessage = "Here's the <blink>latest</blink> <strong>Strategy</strong>..."
//  )
//
//  send a new Mail (
//    from = "john.smith@mycompany.com" -> "John Smith",
//    to = "dev@mycompany.com" :: "marketing@mycompany.com" :: Nil,
//    subject = "Our 5-year plan",
//    message = "Here is the presentation with the stuff we're going to for the next five years.",
//    attachment = new java.io.File("/home/boss/important-presentation.ppt")
//  )
