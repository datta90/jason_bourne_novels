import scala.language.implicitConversions
import org.apache.commons.mail._

package object Mail {

    implicit def stringToSeq(single: String): Seq[String] = Seq(single)
    implicit def liftToOption[T](t: T): Option[T] = Some(t)

    sealed abstract class MailType
    case object Plain extends MailType
    case object Rich extends MailType
    case object MultiPart extends MailType

    case class Mail(
                       from: (String, String), // (email -> name)
                       to: Seq[String],
                       cc: Seq[String] = Seq.empty,
                       bcc: Seq[String] = Seq.empty,
                       subject: String,
                       message: String,
                       richMessage: Option[String] = None,
                       attachments: Seq[java.io.File] = Seq.empty
                   )

    object send {
        def a(mail: Mail) {

            //      import javax.mail.internet.MimeBodyPart

            val format =
                if (mail.attachments.nonEmpty) MultiPart
                else if (mail.richMessage.isDefined) Rich
                else Plain

            val commonsMail: Email = format match {
                case Plain => new SimpleEmail().setMsg(mail.message)
                case Rich => new HtmlEmail().setHtmlMsg(mail.richMessage.get).setTextMsg(mail.message)
                case MultiPart =>
                    val multipartEmail = new MultiPartEmail()
                    mail.attachments.foreach { file =>
                        val attachment = new EmailAttachment()
                        attachment.setPath(file.getAbsolutePath)
                        attachment.setDisposition(EmailAttachment.ATTACHMENT)
                        attachment.setName(file.getName)
                        multipartEmail.attach(attachment)
                    }
                    //          multipartEmail.setContent("html")
                    //          val htmlPart = new MimeBodyPart()
                    //          htmlPart.setContent(mail.richMessage.get, "text/html; charset=utf-8")
                    //          multipartEmail.addPart(htmlPart) //  addBodyPart(htmlPart) // <-- second
                    multipartEmail.setDebug(true)
                    multipartEmail.setMsg(mail.message)



                //
            }

            //  Set authentication from your configuration, sys properties or w/e

            // Can't add these via fluent API because it produces exceptions
            //      mail.to foreach (commonsMail.addTo(_))
            //      mail.cc foreach (commonsMail.addCc(_))
            //      mail.bcc foreach (commonsMail.addBcc(_))

            mail.to foreach commonsMail.addTo
            mail.cc foreach commonsMail.addCc
            mail.bcc foreach commonsMail.addBcc



            commonsMail.setHostName("")
            commonsMail.setAuthentication(mail.from._1,"")




//            for gmail and yahoo use port 587 as we are using startlts
//            and for yandex use port 465

            commonsMail.setSmtpPort(587)
            commonsMail.setStartTLSEnabled(true)
            

//            commonsMail.setSmtpPort(465)
//            commonsMail.setSSLOnConnect(true)




            commonsMail.
                setFrom(mail.from._1, mail.from._2).
                setSubject(mail.subject).
                send()
        }
    }
}
