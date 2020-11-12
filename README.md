# SnackApp
This is an Android app that would be used by a food truck operator to process orders. 

# Note to Reviewers
In doing this programming homework I wanted to demonstrate not just my programming skills but also my ability to architect Android apps. Thus, my app has a fully fledged architecture even though for the scope of the project and simplicity of the app it was kind of overkill. I wanted to note this to make it clear that I don't always overdo projects or write over-complex code when given a task. Additionally, I'm not claiming that this is a perfectly architected that's ready to expand into a complex, fully fledged app but given the time I wrote it in I'm happy with how it turned out. 

I decided to use an MVP(Model-View-Presenter) architecture instead of the MVVP architecture that seems to be the go-to for Android apps these days as it's a lot simpler and a better fit for both the scope of the project itself and the time limitations. 

# Libraries
I really didn't use any libraries outside of the standard Android libraries. 

com.google.code.gson:gson:2.8.6 is the only one I feel like really needs an explanation. I used this to serialize the properties of my SnackItem data class as if it was going to be used with an API, although as we didn't implement that part it doesn't actually achieve anything.

