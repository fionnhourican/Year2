package javasoc;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * The ClientApp class implements interface App.
 * The CLient App is the main class that contains the main method to run the program.
 * It has a method demo that demos the Client App by creating instances of each class
 * and calling their respective methods to print out their information.
 * @see App
 * @author Fionn Hourican
 */

public class ClientApp implements App {

    /**
    * The main method is the entry point of the program.
    * It creates a ClientApp object and calls its demo method, then prints the result to the console.
    * @param args an array of command-line arguments, which are not used in this method.
    */

    public static void main(String[] args) {
        ClientApp clientApp = new ClientApp();
        System.out.println(clientApp.demo());
    }

    /**
     * Variables declared in ClientApp
     */
    /**
     * The useres Inbox
     */
    private Inbox inbox;
    /**
     * The useres Inbox
     */
    private Outbox outbox;

    /**
     * Constructs a new instance of the ClientApp class.
     * Initializes the inbox and outbox fields with new instances of InboxClass and OutboxClass respectively.
     */
    public ClientApp(){
        setInbox();
        setOutbox();
    }

    /**
     * getInbox method declared in Class ClientApp
     * @return inbox - the useres inbox
     * @see App
     * @see Class ClientApp
     */
    @Override
    public Inbox getInbox() {
        return this.inbox;
    }
    /**
     * setInbox method declared in Class ClientApp
     * sets the users inbox
     * @see Class ClientApp
     */
    public void setInbox(){
        this.inbox = new InboxClass();
    }

    /**
     * getOutbox method declared in Class ClientApp
     * @return Outbox - useres outbox
     * @see App
     * @see Class ClientApp
     */
    @Override
    public Outbox getOutbox() {
        return this.outbox;
    }
    
    /**
     * setOutbox method declared in Class ClientApp
     * sets the usres outbox
     * @see Class ClientApp
     */
    public void setOutbox(){
        this.outbox = new OutboxClass();
    }

    /**
     * demo method declared in Class ClientApp
     * This method is used to demonstrate how the JavaVerse application works by simulating user actions.
     * It creates two people, adds them to the JavaVerse network, makes one person follow the other.
     * creates an activity by the followed person, sends the activity to all followers, and then one of the
     * followers likes the activity. The method then prints out information about the people, the activity, and
     * the messages in their inboxes and outboxes.
     * @return String - describes the sequence of actions taken by the method.
     * @see Class ClientApp
     */
    @Override
    public String demo() {

        System.out.println("---------------------------Demo for Client APP---------------------------");
        System.out.println("-------------------------------------------------------------------------");
        System.out.println();

        // Create two people
        Person person1 = new Person("https://harshp.com/me", "Harshvardhan Pandit");
        Person person2 = new Person("https://harshp.com/anotherMe", "HJP");

        // Show that both people created and show all their info.
        System.out.println("--------Person 1 Added--------");
        System.out.println("Name: " + person1);
        System.out.println("URI: " + person1.getURI());
        System.out.println("------------------------------");
        System.out.println("--------Person 2 Added--------");
        System.out.println("Name: " + person2);
        System.out.println("URI: " + person2.getURI());
        System.out.println("------------------------------");
        System.out.println();

        System.out.println("---Person 1 follows Person 2--");
        FollowActivity followActivity = new FollowActivity(person2.getURI(), person2, person1);
        person1.sendToOneFollower(person2, followActivity);

        System.out.println("-----------Person 1-----------");
        System.out.println("Name: " + person1);
        System.out.println("URI: " + person1.getURI());
        System.out.println("Outbox: " + person1.getOutbox());
        System.out.println("Following: " + person1.getFollowing());
        System.out.println("------------------------------");
        System.out.println("-----------Person 2-----------");
        System.out.println("Name: " + person2);
        System.out.println("URI: " + person2.getURI());
        System.out.println("Inbox: " + person2.getInbox());
        System.out.println("Message in Inbox: " + person2.getInbox().getMessages());
        System.out.println("Followers: " + person2.getFollowers());
        System.out.println("------------------------------");

        System.out.println();
        System.out.println("-----HJP creating Activity----");
        CreateActivity createActivity = new CreateActivity(
            "https://example.com/post001",
            Audience.GLOBAL,
            "This is the first post in JavaVerse!",
            "First Post",
            Instant.parse("2023-03-16T16:00:00Z"),
            false,
            person2
        );
        person2.sendToAllFollowers(createActivity);
        System.out.println("-----Actvity information------");
        System.out.println("URI: " + createActivity.getURI());
        System.out.println("Audience: " + createActivity.getAudience());
        System.out.println("Likes: " + createActivity.getLikes());
        System.out.println("Shares: " + createActivity.getShares());
        System.out.println("Content: " + createActivity.getContent());
        System.out.println("Name: " + createActivity.getName());
        System.out.println("Published: " + createActivity.getPublished());
        System.out.println("Deleted: " + createActivity.isDeleted());
        System.out.println("------------------------------");

        // Show that the activity was added to the outbox
        System.out.println("Name: " + person1);
        System.out.println("URI: " + person1.getURI());
        System.out.println("Inbox: " + person1.getInbox());
        System.out.println("Message In Inbox: " + person1.getInbox().getMessages());
        System.out.println("------------------------------");
        System.out.println("Name: " + person2);
        System.out.println("URI: " + person2.getURI());
        System.out.println("Outbox: " + person2.getOutbox());
        System.out.println("Message In Outbox: " + person2.getOutbox().getMessages());
        System.out.println("------------------------------");
        System.out.println();
        System.out.println("------------------------------");
        System.out.println("Person 1 likes Person 2's Post");
        System.out.println("------------------------------");

        LikeActivity LikeActivity = new LikeActivity(createActivity, createActivity.getOwner(), person1);
        person1.sendToOneFollower(person2, LikeActivity);

        System.out.println("Name: " + person1);
        System.out.println("URI: " + person1.getURI());
        System.out.println("Outbox: " + person1.getOutbox());
        System.out.println("Liked: " + person1.getLiked());
        System.out.println("------------------------------");
        System.out.println("Name: " + person2);
        System.out.println("URI: " + person2.getURI());
        System.out.println("Inbox: " + person2.getInbox());
        System.out.println("Message In Inbox: " + person2.getInbox().getMessages());
        System.out.println("Posts Liked:  " + createActivity.getLikes());
        System.out.println();
        System.out.println("--------Person 1 reposts Person 2--------");

        ShareActivity shareActivity = new ShareActivity(createActivity, person2, person1);
        person1.sendToAllFollowers(shareActivity);

        System.out.println("Name: " + person1);
        System.out.println("URI: " + person1.getURI());
        System.out.println("Outbox: " + person1.getOutbox());
        System.out.println("Repost: " + person1.getShares());
        System.out.println("------------------------------");
        System.out.println("Name: " + person2);
        System.out.println("URI: " + person2.getURI());
        System.out.println("Inbox: " + person2.getInbox());
        System.out.println("Message In Inbox: " + person2.getInbox().getMessages());
        System.out.println("Posts Shares:  " + createActivity.getShares());

        DeleteActivity deleteActivity = new DeleteActivity(createActivity);
        System.out.println();
        System.out.println("---------------------------------------------");
        System.out.println("-----------------Delete Post-----------------");
        System.out.println("posts deleted: " + createActivity.isDeleted());
        System.out.println("posts Uri: " + createActivity.getURI());
        System.out.println("posts name: " + createActivity.getName());
        System.out.println();

        System.out.println("--------------------------------");
        System.out.println("---Person 1 unfollows Person 2--");

        UnFollowActivity unfollowActivity = new UnFollowActivity(person2.getURI(), person2, person1);
        person1.sendToOneFollower(person2, unfollowActivity);

        System.out.println("-----------Person 1-----------");
        System.out.println("Name: " + person1.getName());
        System.out.println("URI: " + person1.getURI());
        System.out.println("Following: " + person1.getFollowing());
        System.out.println("------------------------------");
        System.out.println("-----------Person 2-----------");
        System.out.println("Name: " + person2.getName());
        System.out.println("URI: " + person2.getURI());
        System.out.println("Followers: " + person2.getFollowers());


        System.out.println();
        System.out.println("-------------Reading Person1 Inbox--------------");
        System.out.println("Inbox: " + person1.getInbox());
        while (person1.getInbox().getCount() > 0){
            System.out.println("Message In Inbox: " + person1.getInbox().readNext());
        }
        System.out.println("Inbox: " + person1.getInbox());

        System.out.println();
        System.out.println("-------------Reading Person2 Inbox--------------");
        System.out.println("Inbox: " + person2.getInbox());
        while (person2.getInbox().getCount() > 0){
            System.out.println("Message In Inbox: " + person2.getInbox().readNext());
        }
        System.out.println("Inbox: " + person2.getInbox());



        System.out.println();
        return "Demo Finished!";
    }


    /**
     * The FollowActivity class implements the Activity interface.
     * The class represents a "follow" activity in a social network, where one person follows another.
     * @see Activity
     */
    private class FollowActivity implements Activity {

        /**
         * Variables declared in FollowActivity
         */
        /**
         * the uri of the person to be followed
         */
        private String uri;
        /**
         * the person to be followed
         */
        private Person follow;
        /**
         * the person requesting to follow
         */
        private Person follower;

        /**
         * The constructor FollowActivity creates a new instance of a FollowActivity object with the given uri, follow, and follower.
         * @param uri - the uri of the person to be followed
         * @param follow - the person to be followed
         * @param follower - the person requesting to follow
         */
        public FollowActivity(String uri, Person follow, Person follower) {
            setURI(uri);
            setFollowing(follow);
            setFollower(follower);
            followPerson();
        }

        /**
         * followPerson method declared in class FollowActivity
         * Makes the person requesting to follow, follow the person to be follwed
         * @see Class FollowActivity
         */
        public void followPerson(){
            Person follow = getFollowing();
            Person follower = getFollower();

            follower.follow(follow);
        }

        /**
         * getURI method declared in class FollowActivity
         * @return String - URI of the person to be followed
         * @see Class FollowActivity
         * @see Activity
         */
        @Override
        public String getURI() {
            return this.uri;
        }
        /**
         * setURI method declared in class FollowActivity
         * @param uri - The uri of the person to be followed
         * @see Class FollowActivity
         */
        public void setURI(String uri) {
            this.uri = uri;
        }

        /**
         * getFollowing method declared in class FollowActivity
         * @return Person - the person to be followed
         * @see Class FollowActivity
         */
        public Person getFollowing() {
            return this.follow;
        }
        /**
         * setFollowing method declared in class FollowActivity
         * @param person - The person to be followed
         * @see Class FollowActivity
         */
        public void setFollowing(Person person) {
            this.follow = person;
        }

        /**
         * getFollower method declared in class FollowActivity
         * @return Person - The person requesting to follow
         * @see Class FollowActivity
         */
        public Person getFollower() {
            return this.follower;
        }
        /**
         * setFollower method declared in class FollowActivity
         * @param person - The person requesting to follow
         * @see Class FollowActivity
         */
        public void setFollower(Person person) {
            this.follower = person;
        }

        /**
         * toSring method declared in class FollowActivity
         * @return String - follower Followed following
         * @see Class FollowActivity
         */
        @Override
        public String toString(){
            return getFollower() + " Followed " + getFollowing();
        }
    }

    /**
     * The UnFollowActivity class implements the Activity interface.
     * The class represents a "unfollow" activity in a social network, where one person unfollows another.
     * @see Activity
     */
    private class UnFollowActivity implements Activity {

        /**
         * Variables declared in FollowActivity
         */
        /**
         * the uri of the person to be unfollowed
         */
        private String uri;
        /**
         * the person to be unfollowed
         */
        private Person follow;
        /**
         * the person requesting to unfollow
         */
        private Person follower;

        /**
         * The constructor UnFollowActivity creates a new instance of a UnFollowActivity object with the given uri, follow, and follower.
         * @param uri - the uri of the person to be unfollowed
         * @param follow - the person to be unfollowed
         * @param follower - the person requesting to unfollow
         */
        public UnFollowActivity(String uri, Person follow, Person follower) {
            setURI(uri);
            setFollowing(follow);
            setFollower(follower);
            unfollowPerson();
        }

        /**
         * unfollowPerson method declared in class UnFollowActivity
         * Makes the person requesting to unfollow, unfollow the person to be unfollwed
         * @see Class UnFollowActivity
         */
        public void unfollowPerson(){
            Person follow = getFollowing();
            Person follower = getFollower();

            follower.unfollow(follow);
        }

        /**
         * getURI method declared in class UnFollowActivity
         * @return String - URI of the person to be unfollowed
         * @see Class UnFollowActivity
         * @see Activity
         */
        @Override
        public String getURI() {
            return this.uri;
        }
        /**
         * setURI method declared in class UnFollowActivity
         * @param uri - The uri of the person to be unfollowed
         * @see Class UnFollowActivity
         */
        public void setURI(String uri) {
            this.uri = uri;
        }

        /**
         * getFollowing method declared in class UnFollowActivity
         * @return Person - the person to be unfollowed
         * @see Class UnFollowActivity
         */
        public Person getFollowing() {
            return this.follow;
        }
        /**
         * setFollower method declared in class UnFollowActivity
         * @param person - The person requesting to unfollow
         * @see Class UnFollowActivity
         */
        public void setFollowing(Person person) {
            this.follow = person;
        }

        /**
         * getFollower method declared in class UnFollowActivity
         * @return Person - The person requesting to unfollow
         * @see Class UnFollowActivity
         */
        public Person getFollower() {
            return this.follower;
        }
        /**
         * setFollower method declared in class UnFollowActivity
         * @param person - The person requesting to unfollow
         * @see Class UnFollowActivity
         */
        public void setFollower(Person person) {
            this.follower = person;
        }

        /**
         * toSring method declared in class UnFollowActivity
         * @return String - follower unFollowed unfollowing
         * @see Class UnFollowActivity
         */
        @Override
        public String toString(){
            return getFollower() + " unFollowed " + getFollowing();
        }
    }

    /**
     * The LikeActivity class implements the Activity interface.
     * The class represents a "Like" activity in a social network, where one person likes anothers post.
     * @see Activity
     */
    private class LikeActivity implements Activity {

        /**
         * Variables declared in LikeActivity
         */
        /**
         * the uri of the post to be liked
         */
        private String uri;
        /**
         * the person who ownes the post
         */
        private Person poster;
        /**
         * the person requesting to like the Post
         */
        private Person liker;
        /**
         * the post requesting to be liked
         */
        private CreateActivity post;

        /**
         * The constructor LikeActivity creates a new instance of a LikeActivity object with the given uri, follow, and follower.
         * @param post - the post to be likd
         * @param poster - the person who ownes the post
         * @param liker - the person requesting to like the Post
         */
        public LikeActivity(CreateActivity post, Person poster, Person liker) {
            setPost(post);
            setURI();
            setPoster(poster);
            setLiker(liker);
            like();
        }

        /**
         * like method declared in class LikeActivity
         * Adds the post to a likers liked Array
         * @see Class LikeActivity
         */
        public void like(){
            getLiker().like(getURI());
            getPost().addLike(getLiker());;
        }

        /**
         * getURI method declared in class LikeActivity
         * @return String - URI of the post to be liked
         * @see Class LikeActivity
         * @see Activity
         */
        @Override
        public String getURI() {
            return this.uri;
        }
        /**
         * setURI method declared in class LikeActivity
         * @see Class LikeActivity
         */
        public void setURI() {
            this.uri = getPost().getURI();
        }

        /**
         * getPost method declared in class LikeActivity
         * @return Person - The person who ownes the post
         * @see Class LikeActivity
         * @see Activity
         */
        public CreateActivity getPost() {
            return this.post;
        }
        /**
         * setPoster method declared in class LikeActivity
         * @param post - The post being liked
         * @see Class LikeActivity
         */
        public void setPost(CreateActivity post) {
            this.post = post;
        }
        
        /**
         * getPoster method declared in class LikeActivity
         * @return Person - The person who ownes the post
         * @see Class LikeActivity
         * @see Activity
         */
        public Person getPoster() {
            return this.poster;
        }
        /**
         * setPoster method declared in class LikeActivity
         * @param person - The person who ownes the post
         * @see Class LikeActivity
         */
        public void setPoster(Person person) {
            this.poster = person;
        }

        /**
         * getLiker method declared in class LikeActivity
         * @return Person - The person requesting to like the Post
         * @see Class LikeActivity
         * @see Activity
         */
        public Person getLiker() {
            return this.liker;
        }
        /**
         * setLiker method declared in class LikeActivity
         * @param person - The person requesting to like the Post
         * @see Class LikeActivity
         */
        public void setLiker(Person person) {
            this.liker = person;
        }

        /**
         * toSring method declared in class LikeActivity
         * @return String - liker liked posts uri
         * @see Class LikeActivity
         */
        @Override
        public String toString() {
            return  getLiker() + " Liked " + getURI();
        }
    }

    /**
     * The LikeActivity class implements the Activity interface.
     * The class represents a "Like" activity in a social network, where one person likes anothers post.
     * @see Activity
     */
    private class ShareActivity implements Activity {

        /**
         * Variables declared in LikeActivity
         */
        /**
         * the uri of the post to be shared
         */
        private String uri;
        /**
         * the person who ownes the post
         */
        private Person poster;
        /**
         * the person requesting to share the Post
         */
        private Person reposter;
        /**
         * the post requesting to be liked
         */
        private CreateActivity post;

        /**
         * The constructor ShareActivity creates a new instance of a ShareActivity object with the given post, poster, and reposter.
         * @param post - the post to be likd
         * @param poster - the person who ownes the post
         * @param reposter - the person requesting to repost the Post
         */
        public ShareActivity(CreateActivity post, Person poster, Person reposter) {
            setPost(post);
            setURI();
            setPoster(poster);
            setReposter(reposter);
            share();
        }

        /**
         * share method declared in class ShareActivity
         * Adds the post to a share share Array
         * @see Class ShareActivity
         */
        public void share(){
            getReposter().share(getPost().getURI());
            getPost().addShare(getReposter());
        }

        /**
         * getURI method declared in class LikeActivity
         * @return String - URI of the post to be liked
         * @see Class LikeActivity
         * @see Activity
         */
        @Override
        public String getURI() {
            return this.uri;
        }
        /**
         * setURI method declared in class LikeActivity
         * @see Class LikeActivity
         */
        public void setURI() {
            this.uri = getPost().getURI();
        }

        /**
         * getPost method declared in class LikeActivity
         * @return Person - The person who ownes the post
         * @see Class LikeActivity
         * @see Activity
         */
        public CreateActivity getPost() {
            return this.post;
        }
        /**
         * setPoster method declared in class LikeActivity
         * @param post - The post being liked
         * @see Class LikeActivity
         */
        public void setPost(CreateActivity post) {
            this.post = post;
        }
        
        /**
         * getPoster method declared in class LikeActivity
         * @return Person - The person who ownes the post
         * @see Class LikeActivity
         * @see Activity
         */
        public Person getPoster() {
            return this.poster;
        }
        /**
         * setPoster method declared in class LikeActivity
         * @param person - The person who ownes the post
         * @see Class LikeActivity
         */
        public void setPoster(Person person) {
            this.poster = person;
        }

        /**
         * getLiker method declared in class LikeActivity
         * @return Person - The person requesting to like the Post
         * @see Class LikeActivity
         * @see Activity
         */
        public Person getReposter() {
            return this.reposter;
        }
        /**
         * setLiker method declared in class LikeActivity
         * @param person - The person requesting to like the Post
         * @see Class LikeActivity
         */
        public void setReposter(Person person) {
            this.reposter = person;
        }

        /**
         * toSring method declared in class LikeActivity
         * @return String - liker liked posts uri
         * @see Class LikeActivity
         */
        @Override
        public String toString() {
            return  getReposter() + " shared " + getURI() + "By " + getPoster();
        }
    }

    /**
     * class CreateActivity is the child class of StreamObject
     * The CreateActivity class implements the Activity interface.
     * The class represents a "Create activity" activity in a social network, where one person likes anothers post.
     * @see Activity
     * @see StreamObject
     */
    private class CreateActivity extends StreamObject implements Activity {

        /**
         * variables declared in CreateActivity
         */
        Person owner;

        /**
         * The constructor CreateActivity creates a new instance of a CreateActivity object with the given uri,
         * audience, likes, shares, content, name, published, deleted, owner
         * @param uri - the uri of the Activity
         * @param audience - the audience of the Activity
         * @param content - the content of the Activity
         * @param name - the name of the Activity
         * @param published - the publication date of the Activity
         * @param deleted - the deleted state of Activity
         * @param owner - the owner of the Activity
         */
        public CreateActivity(String uri, Audience audience, String content, String name, Instant published, boolean deleted, Person owner) {
            super(uri, audience, content, name, published, deleted);
            setOwner(owner);
            posted();
        }

        /**
         * posted() method declared in class CreateActivity
         * Adds the post to a posters post Array
         * @see Class CreateActivity
         */
        public void posted(){
            getOwner().post(getURI());
        }

        /**
         * setOwner method declared in class CreateActivity
         * @param owner - The owner of Activity
         * @see Class CreateActivity
         */
        public void setOwner(Person owner){
            this.owner = owner;
        }
        /**
         * getOwner method declared in class CreateActivity
         * @return Person - The owner of Activity
         * @see Class CreateActivity
         */
        public Person getOwner(){
            return this.owner;
        }

        /**
         * toSring method declared in class CreateActivity
         * @return String - new post: post name
         * @see Class CreateActivity
         */
        @Override
        public String toString(){
            return  getName() + " By " + getOwner() + "(" + getURI() +")";
        }
    }

    /**
     * The DeleteActivity class implements the Activity interface.
     * The class represents a "Delete activity" activity in a social network, where one person deletes their post.
     * @see Activity
     */
    private class DeleteActivity implements Activity {

        /**
         * activity - The activity to be deleted
         */
        private CreateActivity activity;

        /**
         * The constructor DeleteActivity creates a new instance of a DeleteActivity
         * @param activity - The activity to be deleted
         */
        public DeleteActivity(CreateActivity activity) {
            this.activity = activity;
            delete();
            unlike();
        }

        /**
         * unlike method declared in class Delete
         * removes the post from a likers liked Array
         * @see Class DeleteActivity
         */
        public void unlike(){
            Person owner = getActivity().getOwner();
            owner.unlike(getActivity().getURI());
        }


        /**
         * getActivity method declared in class DeleteActivity
         * @return CreateActivity - The Activity to be deleted
         * @see Class DeleteActivity
         */
        public CreateActivity getActivity() {
            return this.activity;
        }

        /**
         * delete() method declared in class DeleteActivity
         * Sets the posts state as deleted
         * @see Class DeleteActivity
         */
        public void delete(){
            this.activity.setDeleted(true);
        }

        /**
         * getURI method declared in class DeleteActivity
         * @return String - The uri of the activity to  be deleted
         * @see Class DeleteActivity
         */
        @Override
        public String getURI() {
            return this.activity.getURI();
        }

    }

    /**
     * The InboxClass class implements the Inbox interface.
     * The class represents the "Inbox" of a user, where the user can read their messages.
     * @see Inbox
     */
    class InboxClass implements Inbox {

        /**
         * variables declared in InboxClass
         * List of messages in inbox
         */
        private List<Activity> messages;

        /**
         * Initilises Message Array, using setMessages() method
         */
        public InboxClass() {
            setMessages();
        }

        /**
         * readNext method declared in class InboxClass
         * Reads the next message if the user has unread messages, otherwise returns null
         * @return Activity - message array withe read message removed
         * @see Class InboxClass
         * @see Inbox
         */
        @Override
        public Activity readNext() {
            if (messages.isEmpty()) {
                toString();
                return null;
            } else {
                getMessages();
                return messages.remove(0);
            }
        }

        /**
         * getCount method declared in class InboxClass
         * @return int - count of messages in inbox
         * @see Class InboxClass
         * @see Inbox
         */
        @Override
        public int getCount() {
            return messages.size();
        }

        /**
         * getMessages method declared in class InboxClass
         * @return List - The Array of messages
         * @see Class InboxClass
         */
        public List<Activity> getMessages() {
            return this.messages;
        }
        /**
         * setMessages method declared in class InboxClass
         * Initilises Message Array
         * @see Class InboxClass
         */
        public void setMessages(){
            this.messages = new ArrayList<Activity>();
        }

        /**
         * receive method declared in class InboxClass
         * @param activity - The Activity being recieved
         * @return boolean - True if message has been recieved
         * @see Class InboxClass
         */
        @Override
        public boolean receive(Activity activity) {
            return messages.add(activity);
        }

        /**
         * toSring method declared in class InboxClass
         * @return String - if user has messages -> you have count messages else -> Your Inbox is empty
         * @see Class InboxClass
         */
        @Override
        public String toString() {
            if (getCount() > 0) {
                return "You have " + getCount() + " messages";
            } else {
                return "Your Inbox is empty";
            }
        }
    }

    /**
     * The OutboxClass class implements the Outbox interface.
     * The class represents the "Outbox" of a user, where the user can send their messages.
     * @see Outbox
     */
    class OutboxClass implements Outbox {

        /**
         * variables declared in OutboxClass
         * List of messages in Outbox
         */
        private List<Activity> messages;

        /**
         * Initilises Message Array, using setMessages() method
         */
        public OutboxClass() {
            setMessages();
        }

        /**
         * getCount method declared in class OutboxClass
         * @return int - count of messages in outbox
         * @see Class OutboxClass
         * @see Outbox
         */
        @Override
        public int getCount() {
            return messages.size();
        }
        /**
         * getMessages method declared in class OutboxClass
         * @return List - The Array of messages
         * @see Class OutboxClass
         */
        public List<Activity> getMessages() {
            return this.messages;
        }
        /**
         * setMessages method declared in class OutboxClass
         * Initilises Message Array
         * @see Class OutboxClass
         */
        public void setMessages(){
            this.messages = new ArrayList<Activity>();
        }

        /**
         * send method declared in class OutboxClass
         * @param activity - The activity to be sent
         * @return boolean - True if message has been sent
         * @see Class OutboxClass
         */
        @Override
        public boolean send(Activity activity) {
            return messages.add(activity);
        }

        /**
         * deliverNext method declared in class OutboxClass
         * Sends the next message if the user has unsent messages, otherwise returns null
         * @return Activity - message array withe sent message removed
         * @see Class OutboxClass
         * @see Outbox
         */
        @Override
        public Activity deliverNext() {
            if (messages.isEmpty()) {
                toString();
                return null;
            } else {
                return messages.remove(0);
            }
        }

        /**
         * toSring method declared in class OutboxClass
         * @return String - if user has messages -> you have sent count messages else -> Your Outbox is empty
         * @see Class OutboxClass
         */
        @Override
        public String toString() {
            if (getCount() > 0) {
                return "You have sent " + getCount() + " messages";
            } else {
                return "Your outbox is empty";
            }
        }
    }

    /**
     * The Person class represents the "User" of the ClientApp
     */
    class Person {

        /**
         * variables declared in Person
         */
        /**
         * the uri of the Person
         */
        private String uri;
        /**
         * the name of the Person
         */
        private String name;
        /**
         * the inbox of the Person
         */
        private InboxClass inbox;
        /**
         * the outbox of the Person
         */
        private OutboxClass outbox;
        /**
         * the followers of the Person
         */
        private ArrayList<Person> followers;
        /**
         * who the Person is following
         */
        private ArrayList<Person> following;
        /**
         * the posts liked by the Person
         */
        private ArrayList<String> liked;
        /**
         * the posts liked by the Person
         */
        private ArrayList<String> posts;

        /**
         * the posts shared by the Person
         */
        private ArrayList<String> shares;
    
        /**
         * The constructor Person creates a new instance of a Person object with the given uri, name, inbox, outbox,
         * followers, following, and liked
         * @param uri - the uri of the Person
         * @param name - the name of the Person
         */
        public Person(String uri, String name) {
            setURI(uri);
            setName(name);
            setInbox();
            setOutbox();
            setFollowers();
            setFollowing();
            setLiked();
            setPosts();
            setShares();
        }

        /**
         * sendToAllFollowers method declared in class Person
         * Sends the next message to All the useres followers
         * @param activity - The Activity to be sent
         * @see Class Person
         * @see Outbox
         * @see Inbox
         */
        public void sendToAllFollowers(Activity activity){
            getOutbox().send(activity);
            for(Person person : getFollowers()){
                person.getInbox().receive(activity);
            } 
        }

        /**
         * sendToOneFollower method declared in class Person
         * Sends the next message to One the useres followers.
         * @param person - Person message being sent to
         * @param activity - The Activity to be sent
         * @see Class Person
         * @see Outbox
         * @see Inbox
         */
        public void sendToOneFollower(Person person, Activity activity){
            this.getOutbox().send(activity);
            //person.getInbox().receive(activity);

            this.getOutbox().toString();
            if (this.getOutbox().send(activity) == true && person.getInbox().receive(activity)){
                this.getOutbox().deliverNext();
            }
        }

        /**
         * follow method declared in class Person
         * Adds Person to follwing list
         * Adds Person to follower 
         * @param person - The Person to be followed
         * @see Class Person
         */
        public void follow(Person person) {
            following.add(person);
            person.followers.add(this);
        }
    
        /**
         * unfollow method declared in class Person
         * Removes Person to follwing list
         * Removes Person to follower 
         * @param person - The Person to be unfollowed
         * @see Class Person
         */
        public void unfollow(Person person) {
            following.remove(person);
            person.followers.remove(this);
        }
    
        /**
         * setURI method declared in class Person
         * @param uri - The uri of the person
         * @see Class Person
         */
        public void setURI(String uri){
            this.uri = uri;
        }
        /**
         * getURI method declared in class Person
         * @return String - The uri of the person
         * @see Class Person
         */
        public String getURI() {
            return this.uri;
        }
    
        /**
         * setName method declared in class Person
         * @param name - The name of the person
         * @see Class Person
         */
        public void setName(String name){
            this.name = name;
        }
        /**
         * getName method declared in class Person
         * @return String - The name of the person
         * @see Class Person
         */
        public String getName() {
            return this.name;
        }

        /**
         * setInbox method declared in class Person
         * Initilises Persons inbox
         * @see Class Person
         * @see Class InboxClass
         */
        public void setInbox(){
            this.inbox = new ClientApp.InboxClass();
        }
        /**
         * getInbox method declared in class Person
         * @return InboxClass - The Inbox of the person
         * @see Class Person
         */
        public InboxClass getInbox() {
            return this.inbox;
        }
    
        /**
         * setOutbox method declared in class Person
         * Initilises Persons outbox
         * @see Class Person
         * @see Class OutboxClass
         */
        public void setOutbox(){
            this.outbox = new ClientApp.OutboxClass();
        }
        /**
         * getOutbox method declared in class Person
         * @return OutboxClass - The Outbox of the person
         * @see Class Person
         * @see Class OutboxClass
         */
        public OutboxClass getOutbox() {
            return this.outbox;
        }
    
        /**
         * setFollowers method declared in class Person
         * Initilises Persons followers Array
         * @see Class Person
         */
        public void setFollowers() {
            this.followers = new ArrayList<>();
        }
        /**
         * getFollowers method declared in class Person
         * @return ArrayList - The followers of the person
         * @see Class Person
         */
        public ArrayList<Person> getFollowers() {
            return this.followers;
        }

        /**
         * setFollowers method declared in class Person
         * Initilises Persons followers Array
         * @see Class Person
         */
        public void setShares() {
            this.shares = new ArrayList<>();
        }
        /**
         * getFollowers method declared in class Person
         * @return ArrayList - The followers of the person
         * @see Class Person
         */
        public ArrayList<String> getShares() {
            return this.shares;
        }
    
        /**
         * setFollowing method declared in class Person
         * Initilises Persons followering Array
         * @see Class Person
         */
        public void setFollowing(){
            this.following = new ArrayList<>();
        }
        /**
         * getFollowing method declared in class Person
         * @return ArrayList - The followering Array of the person
         * @see Class Person
         */
        public ArrayList<Person> getFollowing() {
            return this.following;
        }
    
        /**
         * setLiked method declared in class Person
         * Initilises Persons Liked Array
         * @see Class Person
         */
        public void setLiked(){
            this.liked = new ArrayList<>();
        }
        /**
         * getLiked method declared in class Person
         * @return ArrayList - The posts liked by the person
         * @see Class Person
         */
        public ArrayList<String> getLiked() {
            return this.liked;
        }

        /**
         * setPosts method declared in class Person
         * Initilises Persons post Array
         * @see Class Person
         */
        public void setPosts(){
            this.posts = new ArrayList<>();
        }
        /**
         * getPosts method declared in class Person
         * @return ArrayList - The posts by the person
         * @see Class Person
         */
        public ArrayList<String> getPosts() {
            return this.posts;
        }
    
        /**
         * like method declared in class Person
         * Adds a stream object to the useres liked Array
         * @param streamObject - The stream object to be added to the array of the person
         * @see Class Person
         */
        public void like(String streamObject) {
            liked.add(streamObject);
        }

        /**
         * like method declared in class Person
         * Adds a stream object to the useres liked Array
         * @param streamObject - The stream object to be added to the array of the person
         * @see Class Person
         */
        public void unlike(String streamObject) {
            liked.remove(streamObject);
        }

        /**
         * share method declared in class Person
         * Adds a Post to the useres shared Array
         * @param streamObject - The stream object to be added to the share array of the person
         * @see Class Person
         */
        public void share(String streamObject) {
            shares.add(streamObject);
        }

        /**
         * post method declared in class Person
         * Adds a stream object to the posts Array
         * @param streamObject - The stream object to be added to the array of the person
         * @see Class Person
         */
        public void post(String streamObject) {
            posts.add(streamObject);
        }
    
        /**
         * toSring method declared in class Person
         * @return String - The name of  the Person
         * @see Class Person
         */
        @Override
        public String toString() {
            return getName();
        }
    }
    
    /**
     * The StreamObject class represents the "Stream Object" used in the ClientApp
     */
    class StreamObject {
    
        /**
         * variables declared in Person
         */
        /**
         * the URI of the StreamObject
         */
        private String uri;
        /**
         * the audience for the StreamObject
         */
        private Audience audience;
        /**
         * the list of of the StreamObject's likes
         */
        private List<Person> likes;
        /**
         * the list of URIs of the StreamObject's shares
         */
        private List<Person> shares;
        /**
         * the content of the StreamObject
         */
        private String content;
        /**
         * the name of the StreamObject
         */
        private String name;
        /**
         * the date the StreamObject was published
         */
        private Instant published;
        /**
         * the deleted status of the StreamObject
         */
        private boolean deleted;
    
        /**
        * Constructs a new StreamObject with the specified URI, audience, likes, shares, content, name,
        * published date, and deleted status.
        * @param uri the URI of the StreamObject
        * @param audience the audience for the StreamObject
        * @param content the content of the StreamObject
        * @param name the name of the StreamObject
        * @param published the date the StreamObject was published
        * @param deleted the deleted status of the StreamObject
        */
        public StreamObject(String uri, Audience audience, String content, String name, Instant published, boolean deleted) {
            setURI(uri);
            setAudience(audience);
            setLikes();
            setShares();
            setContent(content);
            setName(name);
            setPublished(published);
            setDeleted(deleted);
        }
    
        /**
         * addLike method declared in class StreamObject
         * Adds a Person to the posts liked Array
         * @param liker - The person to be added to the array of likes
         * @see Class StreamObject
         * @see Class Person
         */
        public void addLike(Person liker) {
            if (getLikes() != null){
                likes.add(liker);
            }
        }

        /**
         * addShare method declared in class StreamObject
         * Adds a person to the posts shared Array
         * @param reposter - The person to be added to the array of shares
         * @see Class StreamObject
         */
        public void addShare(Person reposter) {
            if (getShares() != null){
                shares.add(reposter);
            }
        }

        /**
         * getURI method declared in class Person
         * @return String - The uri of the person
         * @see Class Person
         */
        public String getURI() {
            if (!isDeleted()){
                return this.uri;
            }
            return "Post does not exist";
        }
        /**
         * setURI method declared in class StreamObject
         * @param uri - The uri of the StreamObject
         * @see Class StreamObject
         */
        public void setURI(String uri) {
            this.uri = uri;
        }
        
        /**
         * getAudience method declared in class StreamObject
         * @return Audience - The audience of the StreamObject
         * @see Class StreamObject
         */
        public Audience getAudience() {
            return this.audience;
        }
        /**
         * setAudience method declared in class StreamObject
         * @param audience - The audience of the StreamObject
         * @see Class StreamObject
         */
        public void setAudience(Audience audience) {
            this.audience = audience;
        }
    
        /**
         * getLikes method declared in class StreamObject
         * @return List - The likes of the StreamObject
         * @see Class StreamObject
         */
        public List<Person> getLikes() {
            return this.likes;
        }
        /**
         * setLikes method declared in class StreamObject
         * @see Class StreamObject
         */
        public void setLikes() {
            this.likes = new ArrayList<Person>();
        }
    
        /**
         * getShares method declared in class StreamObject
         * @return List - The shares of the StreamObject
         * @see Class StreamObject
         */
        public List<Person> getShares() {
            return this.shares;
        }
        /**
         * setShares method declared in class StreamObject
         * @see Class StreamObject
         */
        public void setShares() {
            this.shares = new ArrayList<Person>();
        }
    
        /**
         * getContent method declared in class StreamObject
         * @return String - The content of the StreamObject
         * @see Class StreamObject
         */
        public String getContent() {
            return this.content;
        }
        /**
         * setContent method declared in class StreamObject
         * @param content - The content of the StreamObject
         * @see Class StreamObject
         */
        public void setContent(String content) {
            this.content = content;
        }
        
        /**
         * getName method declared in class StreamObject
         * @return String - The name of the StreamObject
         * @see Class StreamObject
         */
        public String getName() {
            return this.name;
        }
        /**
         * setName method declared in class StreamObject
         * @param name - The name of the StreamObject
         * @see Class StreamObject
         */    
        public void setName(String name) {
            this.name = name;
        }
    
        /**
         * getPublished method declared in class StreamObject
         * @return Instant - The published instant of the StreamObject
         * @see Class StreamObject
         */
        public Instant getPublished() {
            return this.published;
        }
        /**
         * setPublished method declared in class StreamObject
         * @param published - The published instant of the StreamObject
         * @see Class StreamObject
         */ 
        public void setPublished(Instant published) {
            this.published = published;
        }
    
        /**
         * isDeleted method declared in class StreamObject
         * @return boolean - The deleted state of the StreamObject
         * @see Class StreamObject
         */
        public boolean isDeleted() {
            return this.deleted;
        }
        /**
         * setDeleted method declared in class StreamObject
         * @param deleted - The deleted state of the StreamObject
         * @see Class StreamObject
         */
        public void setDeleted(boolean deleted) {
            this.deleted = deleted;
            if (deleted){
                setURI("Post deleted");
                setAudience(Audience.PRIVATE);
                setName("Deleted");
            }
        }

        /**
         * toSring method declared in class StreamObject
         * @return String - The name of  the StreamObject
         * @see Class StreamObject
         */
        @Override
        public String toString() {
            return getName();
        }
    }

}
/**
 * The Activity interface represents an activity that has a URI associated with it.
 */
interface Activity {
    /**
     * getURI method declared in interface Activity
     * @return String - uri associated with the activity
     */
    String getURI();
}

/**
 * The ReceiveMessage interface receives a message and adds it to the Inbox.
 */
interface ReceiveMessage {
    /**
     * receive method declared in interface ReceiveMessage
     * @param activity - The activity message to be recieved
     * @return String - uri associated with the activity
     * @see Inbox
     */
    boolean receive(Activity activity);
}

/**
 * The ReadNextMessage interface removes and retrieves the next message from Inbox.
 */
interface ReadNextMessage {
    /**
     * readNext method declared in interface ReadNextMessage 
     * @return Activity - an Activity, or null if there are no messages
     * @see Inbox
     */
    Activity readNext();
}

/**
 * The Inbox interface provides inbox functionality.
 */
interface Inbox extends ReceiveMessage, ReadNextMessage {
    /**
     * readNext method declared in interface Inbox
     * @return int - count of unread messages in inbox
     * @see Inbox
     */
    int getCount();
}

/**
 * The SendMessage sends a message and adds it to the Outbox.
 */
interface SendMessage {
    /**
     * send method declared in interface SendMessage
     * @param activity - The activity message to be sent
     * @return Activity - sends a message and adds it to the Outbox
     * @see Outbox
     */
    boolean send(Activity activity);
}

/**
 * The DeliverNextMessage  removes and delivers the next message from inbox.
 */
interface DeliverNextMessage {
    /**
     * deliverNext method declared in interface DeliverNextMessage
     * @return Activity - an Activity, or null if there are no messages
     * @see Outbox
     */
    Activity deliverNext();
}

/**
 * The Outbox interface provides outbox functionality.
 */
interface Outbox extends SendMessage, DeliverNextMessage {
    /**
     * readNext method declared in interface Outbox
     * @return int - count of unsent messages in outbox
     * @see Outbox
     */
    int getCount();
}

/**
 * The App interface is the client App that handles inboxes and outboxes.
 */
interface App {
    /**
     * getInbox method declared in interface App
     * @return Inbox - retrieves the inbox
     * @see Inbox
     * @see ClientApp
     */
    Inbox getInbox();
    /**
     * getOutbox method declared in interface App
     * @return Outbox - retrieves the Outbox
     * @see Outbox
     * @see ClientApp
     */
    Outbox getOutbox();
    /**
     * demo method declared in interface App
     * @return String - prints a demo of the app in action
     * @see ClientApp
     */
    String demo();
}

/**
 * enum Audience representing the two possible audiences for a piece of content: private and global.
 */
enum Audience {
    /**
     * PRIVATE content is intended for a limited audience and should not be shared publicly.
     */
    PRIVATE,
    /**
     * GLOBAL content is intended for a wider audience and can be shared publicly.
     */
    GLOBAL;
}