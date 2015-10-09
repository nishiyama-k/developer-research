module.exports = function(grunt) {

	grunt.loadNpmTasks('grunt-contrib-less');
	grunt.loadNpmTasks("grunt-contrib-watch");

	grunt.initConfig({
		less : {
			bootstrap : {
				src : 'src/main/less/bootstrap/bootstrap.less',
				dest : 'src/main/resources/static/css/lib/bootstrap.css'
			},
			theme : {
				src : 'src/main/less/bootstrap/theme.less',
				dest : 'src/main/resources/static/css/lib/bootstrap-theme.css'
			},
			paper : {
				src : 'src/main/less/bootstrap/paper/bootswatch.less',
				dest : 'src/main/resources/static/css/lib/bootswatch.css'
			},
			app : {
				src : 'src/main/less/app.less',
				dest : 'src/main/resources/static/css/app.css'
			}
		},
		watch : {
			less : {
				files : [ 'src/main/less/*.less' ],
				tasks : [ 'less' ]
			}
		}
	});

	// do task
	grunt.registerTask('default', [ 'less' ]);
};
