package group.revealed.model;


import com.google.api.client.util.store.DataStoreFactory;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RequiredArgsConstructor
public abstract class GmailServiceImpl implements GmailService {
/*

    private static final Logger LOGGER = LoggerFactory.getLogger(GmailServiceImpl.class);
    */
/**
     * Be sure to specify the name of your application. If the application name is {@code null} or
     * blank, the application will log a warning. Suggested format is "MyCompany-ProductName/1.0".
     *//*

    private static final String APPLICATION_NAME = "Tamedia Webshop Test Automation Gmail API";    @NonNull
    private Gmail service = getService();
    */
/**
     * Global instance of the scopes required by this class
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/gmail-java-xxx
     *//*

    private static final List<String> SCOPES = Arrays.asList(GmailScopes.MAIL_GOOGLE_COM);
    */
/**
     * Directory to store user credentials.
     *//*

    private static final File DATA_STORE_DIR = new File(ClassLoader.getSystemClassLoader().getResource("group/tx/model/").getFile());
    */
/**
     * Global instance of the JSON factory.
     *//*

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    */
/**
     * Global instance of the {@link DataStoreFactory}. The best practice is to make it a single
     * globally shared instance across your application.
     *//*

    private static FileDataStoreFactory dataStoreFactory;
    */
/**
     * Global instance of the HTTP transport.
     *//*

    private static HttpTransport httpTransport;

    static {
        try {
            dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    */
/**
     * Get list of messages queried by parameters for set amount of seconds as maximum, 180 by default
     *
     * @param mailQuery, secondsToWait
     * @return List<Message>
     * @throws IOException
     *//*

    public List<Message> waitForEmailsToBeReceivedAndReturnThem(MailQuery mailQuery, int... secondsToWait) throws IOException {
        final int timeout = secondsToWait.length > 0 ? secondsToWait[0] : 180;
        try {
            await()
                    .atMost(timeout, SECONDS)
                    .pollInterval(5, SECONDS)
                    .until(() -> listMessagesByQuery(mailQuery) != null);
        } catch (ConditionTimeoutException e) {
            throw new ConditionTimeoutException("Email was not received within " + timeout + " seconds for receiver " + mailQuery.getReceiver());
        }
        return listMessagesByQuery(mailQuery);
    }

    */
/**
     * Get message content by message ID
     *
     * @param messageId
     * @return Message
     * @throws IOException
     *//*

    public Message getMessageById(String messageId) throws IOException {
        return service.users().messages()
                .get("me", messageId)
                .execute();
    }

    */
/**
     * Get message parts for extracting body
     *
     * @param message
     * @return list of message parts
     *//*

    public List<MessagePart> getMessageParts(Message message) {
        return message.getPayload().getParts();
    }

    */
/**
     * Convert message body as HTML string
     *
     * @param messagePart
     * @return message body as HTML
     *//*

    public String getMessageBodyAsHtml(MessagePart messagePart) {
        return StringUtils.newStringUtf8(Base64.decodeBase64(messagePart.getBody().getData()));
    }

    */
/**
     * Delete message by message ID.
     *
     * @param messageId
     * @throws IOException
     *//*

    public void deleteMessage(String messageId) throws IOException {
        service.users().messages()
                .delete("me", messageId)
                .execute();
    }

    */
/**
     * Get list of messages queried by parameters
     *
     * @param mailQuery
     * @return List<Message>
     * @throws IOException
     *//*

    private List<Message> listMessagesByQuery(MailQuery mailQuery) throws IOException {
        final String query = createQuery(mailQuery);

        return service.users().messages()
                .list("me")
                .setQ(query)
                .execute()
                .getMessages();
    }

    */
/**
     * Create query string based on passed parameters
     *
     * @param mailQuery
     * @return query as a string
     *//*

    private String createQuery(MailQuery mailQuery) {

        final String label = mailQuery.getLabel() != null ? "label:" + mailQuery.getLabel() : "";
        final String subject = mailQuery.getSubject() != null ? "subject:" + mailQuery.getSubject() : "";
        final String sender = mailQuery.getSender() != null ? "from: " + mailQuery.getSender() : "";
        final String receiver = mailQuery.getReceiver() != null ? "to: " + mailQuery.getReceiver() : "";
        final String orderCode = mailQuery.getC1OrderCode() != null ? mailQuery.getC1OrderCode() : "";

        StringBuilder query = new StringBuilder();
        if (!label.isEmpty()) query.append(label).append(" ");
        if (!subject.isEmpty()) query.append(subject).append(" ");
        if (!sender.isEmpty()) query.append(sender).append(" ");
        if (!receiver.isEmpty()) query.append(receiver).append(" ");
        if (!orderCode.isEmpty()) query.append(orderCode);

        return String.valueOf(query);
    }

    private Gmail getService() {
        Credential credential = authorize();
        service = new Gmail.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
        return service;
    }

    private Credential authorize() {
        Credential credential = null;
        try (InputStream inputStream = ClassLoader.getSystemClassLoader().getResource("google/client_secret.json").openStream()) {
            GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(inputStream));
            //Build flow and trigger user authorization request.
            GoogleAuthorizationCodeFlow flow =
                    new GoogleAuthorizationCodeFlow.Builder(
                            httpTransport, JSON_FACTORY, clientSecrets, SCOPES)
                            .setDataStoreFactory(dataStoreFactory)
                            .setAccessType("offline")
                            .build();
            credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
        } catch (IOException ioex) {
            LOGGER.error(ioex.getMessage());
        }

        return credential;
    }



*/

}
