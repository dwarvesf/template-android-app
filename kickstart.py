# encoding: utf8

import os
import sys
import shutil
import codecs
import errno
import pystache

renderer = pystache.Renderer()

dst_path = 'project'


def generate_file(src_file_path, dest_file_path, args):
    with codecs.open(src_file_path, 'r', 'utf-8') as srcFile:
        content = srcFile.read()
        if not os.path.exists(os.path.dirname(dest_file_path)):
            os.makedirs(os.path.dirname(dest_file_path))
        with codecs.open(dest_file_path, "w+", 'utf-8') as dstFile:
            dstFile.write(renderer.render(content, args))


def copy(src, dest):
    if not os.path.exists(os.path.dirname(dest)):
        os.makedirs(os.path.dirname(dest))
    try:
        shutil.copytree(src, dest)
    except OSError as e:
        # If the error was caused because the source wasn't a directory
        if e.errno == errno.ENOTDIR:
            shutil.copy(src, dest)
        else:
            print 'Directory not copied. Error: %s' % e


def main(argv):
    project_arch = argv[0]
    project_name = argv[1]
    chosen_package_name = argv[2]
    google_maps_key = argv[3] if argv[3] else "YOUR_GOOGLE_API_KEY"
    fabric_enabled = True if argv[4] else False
    fabric_key = argv[4] if argv[4] else "YOUR_FABRIC_API_KEY"
    src_path = 'TBD'
    original_package_name = 'TBD'
    if project_arch == '1':
        src_path = 'mvp'
        original_package_name = 'com.dwarvesv.mvp'
    if project_arch == '2':
        src_path = 'mvvm-rx'
        original_package_name = 'com.dwarvesv.mvvm'
    if project_arch == '3':
        src_path = 'clean-architecture-mvvm'
        original_package_name = 'com.dwarves.template'

    generate_args = {
        'appName': project_name,
        'packageName': chosen_package_name,
        'googleApiKey': google_maps_key,
        'fabricEnabled': fabric_enabled,
        'fabricKey': fabric_key
    }

    # copy(os.path.join(src_path, 'app', 'src', 'main', 'res'), os.path.join(dst_path, 'app', 'src', 'main', 'res'))

    copy(os.path.join(src_path, 'gradle', 'wrapper'), os.path.join(dst_path, 'gradle', 'wrapper'))

    for path, subdirs, files in os.walk(src_path):
        for name in files:
            if name == '.DS_Store':
                continue

            # if 'src/main/res' in path:
            #     continue

            if 'gradle/wrapper' in path:
                continue

            src_file_path = os.path.join(path, name)

            dst_file_path = os.path.join(path.replace(src_path, dst_path), name). \
                replace(original_package_name.replace('.', '/'), chosen_package_name.replace('.', '/'))

            print 'Creating ' + dst_file_path + '...'

            if name.endswith('.kt') or name.endswith('.java') \
                    or name.endswith('.xml') or name.endswith('.json') \
                    or name.endswith('.gradle') or name.endswith('.pro') or name.endswith('.md'):
                generate_file(src_file_path, dst_file_path, generate_args)
            else:
                copy(src_file_path, dst_file_path)
    pass

    print "Finished creating project in /project"


if __name__ == "__main__":

    if os.path.exists(dst_path):
        print "The 'project' folder already exists!"
        exit(0)

    read_input = None
    if sys.version_info[0] < 3:
        read_input = raw_input
    else:
        read_input = input

    project_arch = None
    while project_arch not in ['1', '2', '3']:
        project_arch = read_input("Please select the architecture:"
                                  "\n[1] MVP"
                                  "\n[2] MVVM"
                                  "\n[3] Clean architecture (MVVM)"
                                  "\n=> ")

    project_name = read_input("Please enter application name:\n=> ")

    project_package = read_input("Please enter package name:\n=> ")

    google_maps_key = read_input("(Optional) Please enter your Google API Key for Google Maps (leave it blank if not use):\n=> ")

    fabric_key = read_input("(Optional) Please enter your Fabric Key to use Crashlytics (leave it blank if not use):\n=> ")

    # main(sys.argv)
    main([project_arch, project_name, project_package, google_maps_key, fabric_key])
