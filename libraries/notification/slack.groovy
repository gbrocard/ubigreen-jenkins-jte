@AfterStep({context.status.equals("FAILURE")})
void call(context) {
    print "slack"
}
